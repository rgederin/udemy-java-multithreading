package com.gederin.multithreading.advanced;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPools {


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i < 6; i++) {
            executorService.submit(new Processor(i));
        }

        executorService.shutdown();

        System.out.println("All tasks submitted.");

        executorService.awaitTermination(1, TimeUnit.HOURS);
    }
}

class Processor implements Runnable {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    public void run() {
        System.out.println("Starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed: " + id);
    }
}
