# Java Multithreaded Web Crawler

A **depth-limited, multi-threaded web crawler** built in Java using [Jsoup](https://jsoup.org/).  
It crawls websites concurrently with configurable worker threads, handles broken or 404 links gracefully, and avoids revisiting URLs using a thread-safe URL store.

---

## Features

- Multi-threaded crawling using **ExecutorService**
- Synchronization and depth management using **Phaser**
- URL deduplication with **ConcurrentHashMap**
- Handles **404 / broken links** gracefully
- Depth-limited crawling
- Configurable number of worker threads
- Prints visited URLs for easy tracking

---

## Requirements

- Java 21 or higher
- Maven (for dependency management)
- Jsoup 1.20.1 (included in `pom.xml`)

---

## Project Structure

```plaintext
java-multithreaded-web-crawler/
├─ src/
│  └─ main/
│     └─ java/
│        └─ org/
│           └─ example/
│              ├─ WebCrawler.java      # Main entry point
│              ├─ CrawlerTask.java     # Runnable task for crawling
│              ├─ URLStore.java        # Thread-safe URL storage
│              └─ URLFetcher.java      # Jsoup-based link fetcher
├─ pom.xml                          # Maven dependencies
└─ README.md


```
---

## How to Run

1. Clone the repo:

```bash
git clone https://github.com/fxn4zus/java-multithreaded-web-crawler.git
cd java-multithreaded-web-crawler
