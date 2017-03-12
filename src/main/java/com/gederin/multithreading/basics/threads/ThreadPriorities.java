package com.gederin.multithreading.basics.threads;

/**
 * Threads priorities
 */
public class ThreadPriorities {

    public static void main(String[] args) {
        new ThreadPriorities();
    }

    public ThreadPriorities() {

        Thread first = new Thread(new ThreadPrioritiesWorker("first"));
        Thread second = new Thread(new ThreadPrioritiesWorker("second"));

        first.setPriority(Thread.MIN_PRIORITY);
        second.setPriority(Thread.MAX_PRIORITY);

        first.start();
        second.start();
    }

    class ThreadPrioritiesWorker implements Runnable {

        private String threadName;

        public ThreadPrioritiesWorker(String threadName) {
            this.threadName = threadName;
        }

        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(threadName);
            }
        }
    }
}