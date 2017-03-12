package com.gederin.multithreading.advanced;


import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    private LocksWorker locksWorker = new LocksWorker();

    public static void main(String[] args) throws InterruptedException {
        new Locks().doWork();
    }

    private void doWork() throws InterruptedException {
        Thread first = new Thread(new Runnable() {
            public void run() {
                try {
                    locksWorker.first();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread second = new Thread(new Runnable() {
            public void run() {
                try {
                    locksWorker.second();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        locksWorker.finished();
    }
}

class LocksWorker {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private int count = 0;

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }

    public void first() throws InterruptedException {
        lock.lock();

        System.out.println("Waiting...");
        condition.await();
        System.out.println("Wake up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void second() throws InterruptedException {
        Thread.sleep(2000);
        lock.lock();

        System.out.println("Press a return key!");
        new Scanner(System.in).nextLine();

        System.out.println("Got a return key!");

        condition.signal();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }
}
