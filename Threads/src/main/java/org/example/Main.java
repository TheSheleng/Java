package org.example;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        long[] numbers = new long[100];
        for (int i = 0; i < 100; i++) numbers[i] = i;

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);
        long result = pool.invoke(task);
        System.out.println("Sum: " + result);
    }
}