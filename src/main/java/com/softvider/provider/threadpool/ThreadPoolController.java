package com.softvider.provider.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping(value = "/thread-pool")
public class ThreadPoolController {
    private static final Logger log = LoggerFactory.getLogger(ThreadPoolController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Map<String, Object>>> home() throws InterruptedException {
        log.info("Starting execute thread pool...");
        ExecutorService executor = Executors.newFixedThreadPool(10); // Using 10 threads in a pool
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
        List<Map<String, Object>> list = new ArrayList<>();
        List<Callable<Map<String, Object>>> tasks = new ArrayList<>();
        for (int i = 0; i<hostList.length; i++) {
            Callable<Map<String, Object>> task = new MyCallable(hostList[i], list, i+1);
            tasks.add(task);
        }
        executor.invokeAll(tasks);
        executor.shutdown();
        log.info("Executor shutdown status {}", executor.isShutdown());
        log.info("Finished execute thread pool.");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public static class MyCallable implements Callable<Map<String, Object>> {
        private final String url;
        private final int order;
        private final List<Map<String, Object>> list;

        MyCallable(String url, List<Map<String, Object>> list, int order) {
            this.url = url;
            this.list = list;
            this.order = order;
        }

        @Override
        public Map<String, Object> call() {
            Map<String, Object> response = new HashMap<>();
            try {
                log.info("Starting execute task number {}", order);
                URL siteURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
                connection.setRequestMethod(HttpMethod.GET.name());
                connection.setConnectTimeout(3000);
                connection.connect();
                log.info("Finished execute task number {} with response code {}", order, connection.getResponseCode());
                response.put("url", this.url);
                response.put("order", this.order);
                response.put("status", connection.getResponseCode());
                list.add(response);
                return response;
            } catch (Exception e) {
                log.error("Error execute task number {}", order);
                response.put("url", this.url);
                response.put("order", this.order);
                response.put("status", e.getMessage());
                list.add(response);
                return response;
            }
        }
    }
}
