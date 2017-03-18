package com.gederin.multithreading.basics.threads;

import com.gederin.multithreading.util.Util;

/**
 * Stop thread via flag
 */
class CounterThread extends Thread {
    public volatile boolean stopped = false;

    private int count = 0;

    public void run() {
        while (!stopped) {
            Util.sleep(1000);
            System.out.println(count++);
        }
    }
}

public class FlagStop {

    public static void main(String[] args) {
        CounterThread thread = new CounterThread();
        thread.start();
        Util.sleep(10000);
        thread.stopped = true;
        System.out.println("exit");
    }

}