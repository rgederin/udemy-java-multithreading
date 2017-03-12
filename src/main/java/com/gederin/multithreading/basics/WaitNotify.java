package com.gederin.multithreading.basics;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WaitNotify {
    private static final int LIMIT = 10;
    private LinkedList<Integer> list = new LinkedList();
    private Object lock = new Object();


    public static void main(String[] args) {
        new WaitNotify().doWork();
    }

    private void doWork(){
        Thread producer = new Thread(new Runnable() {
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            public void run() {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
    }
    private void produce() throws InterruptedException {
        int value = 0;

        while (true){
            synchronized (lock){
                while (list.size() == LIMIT){
                    System.out.println("producer waiting...");
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    private void consume () throws  InterruptedException {
        while (true){
            synchronized (lock){
                while (list.size() == 0){
                    System.out.println("consumer waiting...");
                    lock.wait();
                }
                System.out.print("List size: " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
                Thread.sleep(new Random().nextInt(1000));
            }
        }
    }
}
