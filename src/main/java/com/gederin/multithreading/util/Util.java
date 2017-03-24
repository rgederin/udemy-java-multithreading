package com.gederin.multithreading.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public final class Util {
    private Util() {
    }

    public static void printCountWithSleep(String threadName, int times, long millis) {
        try {
            for (int i = 0; i < times; i++) {
                System.out.println(threadName + " count: " + i);
                Thread.sleep(millis);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void sleep(long seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }
}
