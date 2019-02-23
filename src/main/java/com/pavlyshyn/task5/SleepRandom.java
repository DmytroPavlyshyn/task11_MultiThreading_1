package com.pavlyshyn.task5;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SleepRandom implements Runnable {
    private ScheduledExecutorService executorService;

    public SleepRandom(int numberOfExecutors) {
        executorService = Executors.newScheduledThreadPool(numberOfExecutors);
    }

    @Override
    public void run() {
        int delay = new Random().nextInt(10) + 1;
        executorService.schedule(() -> System.out.println(Thread.currentThread()+" Delay: "+ delay), delay, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        SleepRandom sleepRandom = new SleepRandom(10);
        for(int i = 0; i< 10; i++){
            sleepRandom.run();
        }
    }
}
