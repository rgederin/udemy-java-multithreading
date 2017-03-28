package com.gederin.multithreading.advanced.atomics;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.IntStream;

import static com.gederin.multithreading.util.Util.stop;

/**
 * LongAdder provides methods add() and increment() just like the atomic number classes and is also
 * thread-safe. But instead of summing up a single result this class maintains a set of variables
 * internally to reduce contention over threads. The actual result can be retrieved by calling sum()
 * or sumThenReset().
 *
 * This class is usually preferable over atomic numbers when updates from multiple threads are more
 * common than reads. This is often the case when capturing statistical data, e.g. you want to count
 * the number of requests served on a web server. The drawback of LongAdder is higher memory
 * consumption because a set of variables is held in-memory.
 */
public class LongAdders {
    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 1000)
                .forEach(i -> executor.submit(adder::increment));

        stop(executor);

        System.out.println(adder.sumThenReset());   // => 1000
    }
}
