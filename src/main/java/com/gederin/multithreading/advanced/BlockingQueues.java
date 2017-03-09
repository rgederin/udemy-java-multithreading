package com.gederin.multithreading.advanced;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class BlockingQueues {
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);
    private Random random = new Random();

    public static void main(String[] args) {
        new BlockingQueues().doWork();
    }

    private void doWork(){
        final Thread producer = new Thread(new Runnable() {
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread consumer1 = new Thread(new Runnable() {
            public void run() {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread consumer2 = new Thread(new Runnable() {
            public void run() {
                try {
                    consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer1.start();
        consumer2.start();
    }
    private void produce() throws InterruptedException {
        while (true){
            queue.put(random.nextInt(100));
            Thread.sleep(random.nextInt(200));
        }
    }

    private void consume () throws InterruptedException {
        while (true){
            Integer value = queue.take();
            System.out.println("Value from a queue: " + value + ", queue size: " + queue.size());
            Thread.sleep(random.nextInt(500));
        }
    }
}
