package com.gederin.multithreading.advanced.collections;

import com.gederin.multithreading.util.Util;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue example
 */
public class BlockingQueues {
    private BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);
    private Random random = new Random();

    public static void main(String[] args) {
        new BlockingQueues().doWork();
    }

    private void doWork() {
        Thread producer = new Thread(this::produce);
        Thread consumer1 = new Thread(this::consume);
        Thread consumer2 = new Thread(this::consume);

        producer.start();
        consumer1.start();
        consumer2.start();
    }

    private void produce() {
        while (true) {
            try {
                queue.put(random.nextInt(100));
                Util.sleep(random.nextInt(200));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    private void consume() {
        while (true) {
            Integer value = null;
            try {
                value = queue.take();
                System.out.println("Value from a queue: " + value + ", queue size: " + queue.size());
                Util.sleep(random.nextInt(500));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
