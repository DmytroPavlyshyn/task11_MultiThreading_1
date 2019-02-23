package com.pavlyshyn.task6;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedMethods {

    public void method1() {
        synchronized (this) {
            double result = 0;
            System.out.println("Method 1 starts");
            for (int i = 0; i < 100; i++) {
                result += ((double) i + i + 1) / 2;
            }
            System.out.println("Result = " + result);
            System.out.println("Method 1 ends");
        }
    }

    public synchronized void method2() {
        double result = 0;
        System.out.println("Method 2 starts");
        for (int i = 0; i < 100; i++) {
            result += ((double) i + i + 1) / 2;
        }
        System.out.println("Result = " + result);
        System.out.println("Method 2 ends");

    }

    public synchronized void method3() {
        double result = 0;
        System.out.println("Method 3 starts");
        for (int i = 0; i < 100; i++) {
            result += ((double) i + i + 1) / 2;
        }
        System.out.println("Result = " + result);
        System.out.println("Method 3 ends");

    }
    public void run(){
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(this::method1);
        service.execute(this::method2);
        service.execute(this::method3);
        service.shutdown();
    }

    public static void main(String[] args) {
        SynchronizedMethods synchronizedMethods = new SynchronizedMethods();
        synchronizedMethods.run();
    }
}
