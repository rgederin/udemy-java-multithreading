package com.gederin.multithreading.basics;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Synchronization {
    private Random random = new Random();

    private List<Integer> integers_1 = new ArrayList<Integer>();
    private List<Integer> integers_2 = new ArrayList<Integer>();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private void stageOne() throws InterruptedException {
        synchronized (lock1) {
            Thread.sleep(1);
            integers_1.add(random.nextInt(100));
        }
    }

    private void stageTwo() throws InterruptedException {
        synchronized (lock2) {
            Thread.sleep(1);
            integers_2.add(random.nextInt(100));
        }
    }

    private void process() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Synchronization().doWork();
    }

    public void doWork() throws InterruptedException {
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

    private Thread thread1 = new Thread(new Runnable() {
        public void run() {
            try {
                process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    private Thread thread2 = new Thread(new Runnable() {
        public void run() {
            try {
                process();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}
