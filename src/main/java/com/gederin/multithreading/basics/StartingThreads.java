package com.gederin.multithreading.basics;

import com.gederin.multithreading.util.Util;

public class StartingThreads {
    public static void main(String[] args) {
        RunnerExtend firstRunner = new RunnerExtend("runner 1");
        RunnerExtend secondRunner = new RunnerExtend("runner 2");

        firstRunner.start();
        secondRunner.start();

        Thread firstThread = new Thread(new RunnerImplement("runner 3"));
        Thread secondThread = new Thread(new RunnerImplement("runner 4"));

        firstThread.start();
        secondThread.start();

        new Thread(new Runnable() {
            public void run() {
                Util.printCountWithSleep("runner 5", 5, 500);
            }
        }).start();
    }
}


//extends thread class
class RunnerExtend extends Thread {

    public RunnerExtend(String name) {
        super(name);
    }

    @Override
    public void run() {
        Util.printCountWithSleep(getName(), 5, 500);
    }
}

//implement runnable interface
class RunnerImplement implements Runnable {

    private String name;

    public RunnerImplement(String name) {
        this.name = name;
    }

    public void run() {
        Util.printCountWithSleep(name, 5, 500);
    }
}
