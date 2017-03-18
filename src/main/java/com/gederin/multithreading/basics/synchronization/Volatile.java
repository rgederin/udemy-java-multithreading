package com.gederin.multithreading.basics.synchronization;


import com.gederin.multithreading.util.Util;

import java.util.Scanner;

/**
 * Volitile keyword
 */
public class Volatile {

    public static void main(String[] args) {
        VolatileWorker worker = new VolatileWorker();
        worker.start();

        System.out.println("Press return to stop ...");
        new Scanner(System.in).nextLine();

        worker.shutdown();
    }

}

class VolatileWorker extends Thread {

    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("hello");
            Util.sleep(200);
        }
    }

    public void shutdown() {
        running = false;
    }
}
