package com.gederin.multithreading.advanced.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * A ScheduledExecutorService is capable of scheduling tasks to run either periodically or once
 * after a certain amount of time has elapsed.
 */
public class ScheduledExecutors {
    public static void main(String[] args) throws InterruptedException {
        //delay();
        period();
    }

    /**
     * executing tasks with a fixed time rate, e.g. once every second
     */
    private static void period() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        int initialDelay = 0;
        int period = 2;

        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }

    /**
     * This code sample schedules a task to run after an initial delay of three seconds has passed
     */
    private static void delay() throws InterruptedException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        TimeUnit.MILLISECONDS.sleep(1337);

        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.println("Remaining Delay: " + remainingDelay + " ms");

        executor.shutdown();
    }
}
