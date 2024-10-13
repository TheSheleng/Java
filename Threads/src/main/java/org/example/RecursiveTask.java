package org.example;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start, end;

    public SumTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= 10) {
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            SumTask task1 = new SumTask(numbers, start, mid);
            SumTask task2 = new SumTask(numbers, mid, end);
            invokeAll(task1, task2);
            return task1.join() + task2.join();
        }
    }
}
