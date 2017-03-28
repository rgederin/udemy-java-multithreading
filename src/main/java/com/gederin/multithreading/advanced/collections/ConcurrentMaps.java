package com.gederin.multithreading.advanced.collections;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMaps {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        map.put("foo", "bar");
        map.put("han", "solo");
        map.put("r2", "d2");
        map.put("c3", "p0");

        /**
         * The method forEach() accepts a lambda expression of type BiConsumer with both the key
         * and value of the map passed as parameters. It can be used as a replacement
         * to for-each loops to iterate over the entries of the concurrent map.
         * The iteration is performed sequentially on the current thread.
         */
        map.forEach((key, value) -> System.out.printf("%s = %s\n", key, value));

        /**
         * The method putIfAbsent() puts a new value into the map only if no value
         * exists for the given key. At least for the ConcurrentHashMap implementation of
         * this method is thread-safe just like put()
         * so you don't have to synchronize when accessing the map concurrently from different threads
         */
        String existedValue = map.putIfAbsent("c3", "p1");
        System.out.println(existedValue);    // p0


        /**
         * The method getOrDefault() returns the value for the given key.
         * In case no entry exists for this key the passed default value is returned:
         */
        String defaultValue = map.getOrDefault("hi", "there");
        System.out.println(defaultValue);    // there

        /**
         * The method replaceAll() accepts a lambda expression of type BiFunction.
         * BiFunctions take two parameters and return a single value.
         * In this case the function is called with the key and the value of
         * each map entry and returns a new value to be assigned for the current key:
         */
        map.replaceAll((key, value) -> "r2".equals(key) ? "d3" : value);
        System.out.println(map.get("r2"));    // d3

        /**
         * Instead of replacing all values of the map compute() let's us transform a single entry.
         * The method accepts both the key to be computed
         * and a bi-function to specify the transformation of the value.
         *
         * In addition to compute() two variants exist: computeIfAbsent() and computeIfPresent().
         * The functional parameters of these methods only get called
         * if the key is absent or present respectively.
         */
        map.compute("foo", (key, value) -> value + value);
        System.out.println(map.get("foo"));   // barbar

        /**
         * Finally, the method merge() can be utilized to unify a new value with an
         * existing value in the map. Merge accepts a key, the new value to be merged
         * into the existing entry and a bi-function to specify the merging behavior of both values
         */
        map.merge("foo", "boo", (oldVal, newVal) -> newVal + " was " + oldVal);
        System.out.println(map.get("foo"));   // boo was foo
    }
}
