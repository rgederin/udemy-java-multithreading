package com.gederin.multithreading.advanced.synchronizers;

import com.gederin.multithreading.util.Util;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java program to demonstrate how to use CountDownLatch in Java. CountDownLatch
 * is useful if you want to start main processing thread once its dependency is
 * completed
 */
public class CountDownLatches {

    public static void main(String args[]) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(3);
        Thread cacheService = new Thread(new Service("cache service", 1000, latch));
        Thread alertService = new Thread(new Service("alert service", 2000, latch));
        Thread validationService = new Thread(new Service("validation service", 3000, latch));

        cacheService.start();
        alertService.start();
        validationService.start();

        /**
         *
         * Application should not start processing any thread until all service is up and ready to do there job.
         * Countdown latch is idle choice here, main thread will start with count 3 and wait until
         * count reaches zero.
         * Each thread once up and read will do a count down.
         * This will ensure that main thread is not started processing until all services is up.
         *
         * count is 3 since we have 3 Threads (Services)
         *
         **/

        latch.await();
        System.out.println("All services are up, Application is starting now");
    }

}


/**
 * Service class which will be executed by Thread using CountDownLatch
 * synchronizer.
 */
class Service implements Runnable {
    private final String name;
    private final int timeToStart;
    private final CountDownLatch latch;

    public Service(String name, int timeToStart, CountDownLatch latch) {
        this.name = name;
        this.timeToStart = timeToStart;
        this.latch = latch;
    }

    @Override
    public void run() {
        Util.sleep(timeToStart);
        System.out.println(name + " is up...");
        // reduce count of CountDownLatch by 1
        latch.countDown();
    }
}