package com.ghj.demo;

import com.ghj.countdown.CountDownLunchTest1;

import java.util.concurrent.*;

public class TestMain {

    public static void main(String[] args) {
        RedPack redPack = new RedPack(100,5);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        CountDownLatch latch = new CountDownLatch(5);
        try {
            LinkedBlockingQueue<UserPack> userPacks= redPack.createRedPack();
            for (int i = 0; i < 5; i++) {
                executor.execute(new CountRedPack(userPacks,i+"号",latch));
            }
            latch.await();
            executor.shutdown();
            System.out.println("结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
