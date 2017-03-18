package com.gederin.multithreading.basics.synchronization;


import com.gederin.multithreading.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * synchronization blocks
 */
public class Synchronization {
    private final Random random = new Random();

    private List<Integer> integers_1 = new ArrayList<Integer>();
    private List<Integer> integers_2 = new ArrayList<Integer>();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private void stageOne() {
        synchronized (lock1) {
            Util.sleep(1);
            integers_1.add(random.nextInt(100));
        }
    }

    private void stageTwo() {
        synchronized (lock2) {
            Util.sleep(1);
            integers_2.add(random.nextInt(100));
        }
    }

    private void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Synchronization().doWork();
    }

    private void doWork() throws InterruptedException {
        Thread thread1 = new Thread(this::process);
        Thread thread2 = new Thread(this::process);

        System.out.println("starting ... ");

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        long end = System.currentTimeMillis();

        System.out.println("Time: " + (end - start));
        System.out.println("List 1: " + integers_1.size());
        System.out.println("List 2: " + integers_2.size());
    }
}
