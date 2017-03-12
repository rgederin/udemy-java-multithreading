package com.gederin.multithreading.basics.threads;

/**
 * Stop thread via interrupt method
 */
public class InterruptStop {
    public static void main(String[] args) {
        try {
            new InterruptStop().test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void test() throws InterruptedException {
        Thread thread = new Thread(new InterruptWorker());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    class InterruptWorker implements Runnable {

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                // continue processing
                System.out.println("ping");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // good practice
                    Thread.currentThread().interrupt();
                    System.out.println("Thread was interrupted. Interrupted = "
                            + Thread.currentThread().isInterrupted());
                    return;
                }
            }
        }
    }
}