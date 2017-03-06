package com.gederin.multithreading.basics;


import java.util.Scanner;

public class Volitile {
    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();

        System.out.println("Press return to stop ...");
        new Scanner(System.in).nextLine();

        processor.shutdown();
    }

}

class Processor extends Thread {

    private boolean running = true;

    public void run() {
        try {
            while (running) {
                System.out.println("hello");
                Thread.sleep(200);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void shutdown() {
        running = false;
    }
}
