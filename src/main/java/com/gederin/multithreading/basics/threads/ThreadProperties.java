package com.gederin.multithreading.basics.threads;

/**
 * Some thread properties
 */
public class ThreadProperties {

    public static void main(String[] args) {
        new ThreadProperties().test();
    }

    private void test() {
        Thread thread = Thread.currentThread();
        System.out.println("Thread id: " + thread.getId());
        System.out.println("Thread name: " + thread.getName());
        System.out.println("Thread priority: " + thread.getPriority());
        System.out.println("Thread state: " + thread.getState());
        System.out.println("Thread group: " + thread.getThreadGroup());
    }
}