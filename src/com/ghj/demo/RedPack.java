package com.ghj.demo;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RedPack {

    private int total;

    private int number;


    public RedPack(int total, int number) {
        this.total = total;
        this.number = number;
    }


    public LinkedBlockingQueue createRedPack() throws InterruptedException {
        LinkedBlockingQueue<UserPack> queue = new LinkedBlockingQueue<UserPack>(number);

        for (int i = 0; i < number; i++) {
            if (i == number - 1) {
                System.out.println("产生红包" + total);
                UserPack userPack = new UserPack("红包" + i, total);
                queue.put(userPack);
            } else {
                Random random = new Random(total);
                int money = random.nextInt(total);
                System.out.println("产生红包" + money);
                UserPack userPack = new UserPack("红包" + i, money);
                queue.put(userPack);
                total = total - money;
            }
        }

        return queue;
    }


}
