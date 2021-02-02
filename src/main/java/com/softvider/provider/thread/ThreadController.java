package com.softvider.provider.thread;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadController {

    private static final int THREAD_POOL = 10;

    public static void main(String args[]) throws Exception {
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
//         Wait until all threads are finish
        while (true) {
            if (executor.isTerminated()) break;
        }
        for (String item : list){
            System.out.println("RESULT :"+ item);
        }
        long end = new Date().getTime();
        System.out.println("Time to process: "+ (start - end));
        System.out.println("\nFinished all threads");
    }

    public static class MyCallable implements Callable<String> {
        private final String url;
        private final List<String> list;

        MyCallable(String url, List<String> list) {
            this.url = url;
            this.list = list;
        }

        @Override
        public String call() throws Exception {
            String result = "";
            int code = 200;
            try {
                URL siteURL = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(3000);
                connection.connect();

                code = connection.getResponseCode();
                if (code == 200) {
                    result = "-> Green <-\t" + "Code: " + code;
                    ;
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
