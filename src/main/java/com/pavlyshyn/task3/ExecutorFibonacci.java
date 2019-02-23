package com.pavlyshyn.task3;

import com.pavlyshyn.task2.Fibonacci;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

public class ExecutorFibonacci {
    void run(ExecutorService e, int number) {
        for (int i = 1; i <= number; i++) {
            e.execute(new Fibonacci(i));
        }
        e.shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorFibonacci executorFibonacci = new ExecutorFibonacci();
        System.out.println("CachedThreadPool");
        executorFibonacci.run(newCachedThreadPool(), 10);
        Thread.sleep(1000);
        System.out.println("SingleThreadExecutor");
        executorFibonacci.run(newSingleThreadExecutor(), 10);
        Thread.sleep(1000);
        System.out.println("FixedThreadPool");
        executorFibonacci.run(newFixedThreadPool(10), 10);
        Thread.sleep(1000);

    }
}
