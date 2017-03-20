package com.gederin.multithreading.advanced.synchronizers;


import com.gederin.multithreading.util.Util;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * The Exchanger class is meant for exchanging data between two threads. It
 * waits until both the threads have called the exchange() method
 *
 * When both threads have called the exchange() method, the Exchanger object
 * actually exchanges the data shared by the threads with each other. This class
 * is useful when two threads need to synchronize between them and continuously
 * exchange data.
 *
 */
public class Exchangers {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        Thread producer = new Thread(new IntegerProducer(exchanger));
        Thread consumer = new Thread(new IntegerConsumer(exchanger));

        producer.start();
        consumer.start();
    }
}

class IntegerProducer implements Runnable {
    private Exchanger<Integer> exchanger;
    private Random random = new Random();


    public IntegerProducer(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("create integer value... ");
            Util.sleep(random.nextInt(3000));
            System.out.println("integer value created, passing to exchanger");
            try {
                exchanger.exchange(random.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class IntegerConsumer implements Runnable {
    private Exchanger<Integer> exchanger;

    public IntegerConsumer(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("received integer from a producer: " + exchanger.exchange(null) + "\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
