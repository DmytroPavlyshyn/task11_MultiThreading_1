package com.pavlyshyn.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FibonacciSum implements Callable<Integer> {
    private int count;
    private final int n;

    public FibonacciSum(int n) {
        this.n = n;
    }

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public Integer call() {
        Integer sum = 0;
        for (int i = 0; i < n; i++)
            sum += next();
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = new ArrayList<>();
        for(int i = 1; i<=10; i++){
            futures.add(service.submit(new FibonacciSum(i)));
        }
        service.shutdown();
        TimeUnit.SECONDS.sleep(1);
        for(Future<Integer> future: futures){
            if(future.isDone()){
                try {
                    System.out.println(future.get());
                } catch (ExecutionException|InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
