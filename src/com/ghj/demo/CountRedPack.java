package com.ghj.demo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class CountRedPack implements Runnable {

    private CountDownLatch latch;
    private LinkedBlockingQueue<UserPack> userPacks;
    private String name;

    public CountRedPack(LinkedBlockingQueue<UserPack> userPacks, String name,CountDownLatch latch) {
        this.userPacks = userPacks;
        this.name = name;
        this.latch=latch;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            UserPack userPack = null;
            try {
                Thread.sleep(new Random(5000).nextInt(5000));
                userPack = userPacks.take();
            } catch (InterruptedException e) {
                break;
            }
            System.out.println(name + "抢到了-->" + userPack.getName()+"红包"+userPack.getMoney()+"元");
            userPacks.remove(userPack);
            latch.countDown();
        }
    }
}
