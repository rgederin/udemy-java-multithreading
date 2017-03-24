package com.gederin.multithreading.advanced.atomics;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.gederin.multithreading.util.Util.stop;

/**
 * An operation is atomic when you can safely perform the operation in parallel on multiple threads
 * without using the synchronized keyword or locks
 *
 * Other useful atomic classes are AtomicBoolean, AtomicLong and AtomicReference.
 */
public class AtomicIntegers {
    public static void main(String[] args) {
        incremenAndGet();
        updateAndGet();
    }

    /**
     * By using AtomicInteger as a replacement for Integer we're able to increment the number
     * concurrently in a thread-safe manor without synchronizing the access to the variable. The
     * method incrementAndGet() is an atomic operation so we can safely call this method from
     * multiple threads.
     */
    private static void incremenAndGet() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));
        stop(executor);

        System.out.println(atomicInt.get());
    }

    /**
     * The method updateAndGet() accepts a lambda expression in order to perform arbitrary
     * arithmetic operations upon the integer
     */
    private static void updateAndGet() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.updateAndGet(n -> n + 2);
                    executor.submit(task);
                });
        stop(executor);

        System.out.println(atomicInt.get());    // => 2000
    }

    /**
     * The method accumulateAndGet() accepts another kind of lambda expression of type
     * IntBinaryOperator
     */
    private static void accumulateAndGet() {
        AtomicInteger atomicInt = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, (n, m) -> n + m);
                    executor.submit(task);
                });

        stop(executor);

        System.out.println(atomicInt.get());    // => 499500
    }


}
