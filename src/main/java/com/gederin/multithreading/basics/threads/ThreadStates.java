package com.gederin.multithreading.basics.threads;

/**
 * Very simple example for states (for only 3 states)
 */
public class ThreadStates {

    public static void main(String[] args) {
        try {
            new ThreadStates().test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test() throws InterruptedException {
        Thread thread = new Thread(new ThreadStatesRunnable());
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        thread.join();
        System.out.println(thread.getState());
    }

    class ThreadStatesRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("ping");
            }
        }
    }
}