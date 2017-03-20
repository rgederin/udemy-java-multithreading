package com.gederin.multithreading.advanced.synchronizers;

import com.gederin.multithreading.util.Util;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by rgederin on 3/20/17.
 */
public class Semaphores {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executorService.submit(() -> Connection.getInstance().connect());
        }

        executorService.shutdown();
    }
}

class Connection {
    private static Connection instance = new Connection();
    private Semaphore semaphore = new Semaphore(10);
    private int connections = 0;

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect(){
        try {
            semaphore.acquire();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }

        try {
            doConnect();
        }finally{
            semaphore.release();
        }
    }
    public void doConnect(){

        synchronized (this) {
            System.out.println("open connection...");
            connections++;
            System.out.println("current connections amount: " + connections);
        }

        Util.sleep(5000);

        synchronized (this) {
            System.out.println("closec onnection...");
            connections--;
            System.out.println("current connections amount: " + connections);
        }
    }
}
