package com.softvider.provider.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/thread")
public class ThreadController {
    private static final Logger log = LoggerFactory.getLogger(ThreadController.class);
    private static final int THREAD_POOL = 10;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<String> home() {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL);
        String[] hostList = {
            "http://crunchify.com",
            "http://yahoo.com",
            "http://www.ebay.com",
            "http://google.com",
            "http://www.example.co",
            "https://paypal.com",
            "http://bing.com/",
            "http://techcrunch.com/",
            "http://mashable.com/",
            "http://thenextweb.com/",
            "http://wordpress.com/",
            "http://wordpress.org/",
            "http://example.com/",
            "http://sjsu.edu/",
            "http://ebay.co.uk/",
            "http://google123.co.uk/",
            "http://wikipedia.org/",
            "http://en.wikipedia.org"
        };

        long start = new Date().getTime();
        List<String> list = new ArrayList<>();
        for (String url : hostList) {
            Callable<String> worker = new MyCallable(url, list);
            executor.submit(worker);
        }
        executor.shutdown();
        /*--- Wait until all threads are finish ---*/
        while (true) {
            if (executor.isTerminated()) break;
        }
        for (String item : list){
            log.info("Result -> {}", item);
        }
        long end = new Date().getTime();
        log.info("Time to process -> {}", (start - end));
        log.info("\nFinished all threads");

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    public static class MyCallable implements Callable<String> {
        private final String url;
        private final List<String> list;

        MyCallable(String url, List<String> list) {
            this.url = url;
            this.list = list;
        }

        @Override
        public String call() {
            String result;
            int code;
            try {
                URL siteURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(3000);
                connection.connect();

                code = connection.getResponseCode();
                if (code == 200) {
                    result = "-> Green <-\t" + "Code: " + code;
                } else {
                    result = "-> Yellow <-\t" + "Code: " + code;
                }
                list.add(url +": "+ result);
                return result;
            } catch (Exception e) {
                result = "-> Red <-\t" + "Wrong domain - Exception: " + e.getMessage();
                list.add(url +": "+ result);
                return result;
            }
        }
    }
}
