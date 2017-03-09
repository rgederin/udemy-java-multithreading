package com.gederin.multithreading.advanced;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatches {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i<1; i++){
            executorService.submit(new LatchProcessor(latch));
        }

        latch.await();
        executorService.shutdown();

        System.out.println("Completed");
    }
}

class LatchProcessor implements Runnable {
    private CountDownLatch latch;

    public LatchProcessor(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        System.out.println("Started");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
    }
}
