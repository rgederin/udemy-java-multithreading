package com.gederin.multithreading.util;

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

    public static void sleep (long milliseconds){
        try {
            Thread.sleep(milliseconds);
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
