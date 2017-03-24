package com.gederin.multithreading.advanced.locks;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static com.gederin.multithreading.util.Util.sleep;
import static com.gederin.multithreading.util.Util.stop;

/**
 * The class ReentrantLock is a mutual exclusion lock with the same basic behavior as the implicit
 * monitors accessed via the synchronized keyword but with extended capabilities. As the name
 * suggests this lock implements reentrant characteristics just as implicit monitors.
 */
public class ReentrantLocks {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    private void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    private void doWork() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(this::increment));

        stop(executor);

        System.out.println(count);
    }


    private void lockProperties() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {
            lock.lock();
            try {
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        stop(executor);
    }

    public static void main(String[] args) {
        new ReentrantLocks().doWork();
        new ReentrantLocks().lockProperties();
    }

}
