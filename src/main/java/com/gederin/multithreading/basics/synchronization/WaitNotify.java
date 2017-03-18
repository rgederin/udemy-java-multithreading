package com.gederin.multithreading.basics.synchronization;

import java.util.LinkedList;
import java.util.Random;

public class WaitNotify {
    private static final int LIMIT = 10;
    private LinkedList<Integer> list = new LinkedList();
    private Object lock = new Object();


    public static void main(String[] args) {
        new WaitNotify().doWork();
    }

    private void doWork() {
        Thread producer = new Thread(this::produce);
        Thread consumer = new Thread(this::consume);

        producer.start();
        consumer.start();
    }

    private void produce() {
        int value = 0;

        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    System.out.println("producer waiting...");
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    private void consume() {
        while (true) {
            synchronized (lock) {
                while (list.isEmpty()) {
                    System.out.println("consumer waiting...");
                    try {
                        lock.wait();
                    }catch (InterruptedException ex){
                        ex.printStackTrace();
                    }

                }
                System.out.print("List size: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
                try {
                    Thread.sleep(new Random().nextInt(1000));
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

            }
        }
    }
}
