package com.jian8.juc.conditionThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        cyclicBarrierTest1();
    }

    public static void cyclicBarrierTest() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("====召唤神龙=====");
        });
        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t收集到第" + tempInt + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }
    }

    public static void cyclicBarrierTest1(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () ->{
            System.out.println("集齐了7颗，召唤神龙！！");
        });

        for (int i = 1; i <=7 ; i++) {
            final int temp = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName()+"，收集到第"+temp+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },""+i).start();

        }
    }
}
