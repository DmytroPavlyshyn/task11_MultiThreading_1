package com.pavlyshyn.task2;

import java.util.Arrays;


public class Fibonacci extends Thread {
    private int count;
    private final int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public void run() {
        Integer[] sequence = new Integer[n];
        for (int i = 0; i < n; i++)
            sequence[i] = next();
        System.out.println(
                "Sequence of " + n + ": " + Arrays.toString(sequence));
    }

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++)
            new Thread(new Fibonacci(i)).start();

    }
}