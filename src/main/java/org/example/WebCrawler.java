package org.example;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class WebCrawler {
    private static Phaser phaser;
    private static ExecutorService executorService;

    public static void submitTask(URLStore urlStore, URLFetcher urlFetcher, int maxDepth, int currentDepth) {
        executorService.submit(new CrawlerTask(urlStore, urlFetcher, maxDepth, currentDepth, phaser));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your url: ");
        String url = scanner.nextLine();

        System.out.println("Enter the depth of the crawler: ");
        final int maxDepth = scanner.nextInt();

        System.out.println("Enter the number of workers: ");
        final int MAX_THREADS = scanner.nextInt();

        URLStore urlStore = new URLStore();
        URLFetcher urlFetcher = new URLFetcher();
        phaser = new Phaser(1);

        executorService = Executors.newFixedThreadPool(MAX_THREADS);
        urlStore.addUrl(url);

        long startTime = System.currentTimeMillis();
        submitTask(urlStore,urlFetcher,maxDepth,1);
        phaser.awaitAdvance(phaser.getPhase());

        executorService.shutdown();

        System.out.println("Time taken : " + (System.currentTimeMillis()-startTime)/100 + "sec");
    }
}
