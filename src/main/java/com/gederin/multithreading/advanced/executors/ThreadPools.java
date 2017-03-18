package com.gederin.multithreading.advanced.executors;


import com.gederin.multithreading.util.Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ExecutorService example with fixedThreadPool
 */
public class ThreadPools {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i < 6; i++) {
            executorService.submit(new ThredPoolsWorker(i));
        }

        executorService.shutdown();
        System.out.println("All tasks submitted.");

        long before = System.currentTimeMillis();
        executorService.awaitTermination(1, TimeUnit.HOURS);
        long after = System.currentTimeMillis();

        System.out.println("Five threads finished in: " + (after - before) + " ms");
    }
}

class ThredPoolsWorker implements Runnable {
    private int id;

    public ThredPoolsWorker(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);
        Util.sleep(5000);
        System.out.println("Completed: " + id);
    }
}
