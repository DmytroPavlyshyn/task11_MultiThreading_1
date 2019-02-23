package com.pavlyshyn.task1;

import java.util.concurrent.TimeUnit;

public class PingPong {
    private volatile Integer counter;

    void run(int n) {
        counter = n;
        Thread ping = new Thread(() -> {
            synchronized (counter) {
                for (int i = 0; i < counter; i++) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        counter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\033[31mPing");
                    counter.notify();
                }
            }
        });
        Thread pong = new Thread(() -> {
            synchronized (counter) {

                for (int i = 0; i < counter; i++) {
                    counter.notify();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        counter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("\033[36m\tPong");
                }
            }
        });
        ping.start();
        pong.start();
    }

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        pingPong.run(5);
    }
}


