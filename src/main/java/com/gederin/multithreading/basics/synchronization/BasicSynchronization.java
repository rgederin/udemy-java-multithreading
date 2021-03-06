package com.gederin.multithreading.basics.synchronization;


/**
 * synchronized for method
 */
public class BasicSynchronization {
    private static final int TEN_K = 10000;
    private int count;

    private void increment() {
        for (int i = 0; i < TEN_K; i++) {
            count++;
        }
    }

    private synchronized void synchronizedIncrement() {
        for (int i = 0; i < TEN_K; i++) {
            count++;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BasicSynchronization app = new BasicSynchronization();

        app.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(this::synchronizedIncrement);
        Thread thread2 = new Thread(this::synchronizedIncrement);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count with synchronization: " + count);

        thread1 = new Thread(this::increment);
        thread2 = new Thread(this::increment);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Count without synchronization: " + count);
    }
}
