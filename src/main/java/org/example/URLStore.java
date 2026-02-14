package org.example;

import java.util.concurrent.*;

public class URLStore {
    private final ConcurrentHashMap<String, Boolean> visitedUrls = new ConcurrentHashMap<>();
    private final BlockingQueue<String> urlQueue = new LinkedBlockingQueue<>();

    public boolean addUrl(String url) {
        if (visitedUrls.putIfAbsent(url, true) == null) { // if the url is not present in the map then it returns null and stores that url in the map
            urlQueue.offer(url);
            return true;
        }
        return false;
    }

    public String getNextUrl() {
        return urlQueue.poll();
    }

    public boolean isQueueEmpty() {
        return urlQueue.isEmpty();
    }
}
