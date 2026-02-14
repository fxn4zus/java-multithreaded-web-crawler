package org.example;

import java.util.Set;
import java.util.concurrent.Phaser;

public class CrawlerTask implements Runnable {
    private final URLStore urlStore;
    private final URLFetcher urlFetcher;
    private final int maxDepth;
    private final int currentDepth;
    private final Phaser phaser;

    public CrawlerTask(URLStore urlStore, URLFetcher urlFetcher, int maxDepth, int currentDepth, Phaser phaser) {
        this.urlStore = urlStore;
        this.urlFetcher = urlFetcher;
        this.maxDepth = maxDepth;
        this.currentDepth = currentDepth;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        try {
            String url = urlStore.getNextUrl();
            System.out.println(Thread.currentThread().getName() + " visiting " + url);
            if (url == null || currentDepth >= maxDepth) {
                return;
            }

            Set<String> links = urlFetcher.fetchLinks(url);
            for (String link : links) {
                String normalizedUrl = link.replaceAll("/$", ""); // remove trailing slash
                if(urlStore.addUrl(normalizedUrl)){
                    phaser.register();
                    WebCrawler.submitTask(urlStore, urlFetcher, maxDepth, currentDepth + 1);
                }

            }
        } catch (Exception e) {
            System.out.println("Error occured!! " + e.getMessage());
            e.printStackTrace();
        } finally {
            phaser.arriveAndDeregister();
        }
    }
}
