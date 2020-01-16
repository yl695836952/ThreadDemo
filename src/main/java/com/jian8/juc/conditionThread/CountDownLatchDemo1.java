package com.jian8.juc.conditionThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch
 * @author yanglin
 * @create 2020-01-15 15:20
 */
public class CountDownLatchDemo1 {
  public static void main(String[] args) {
    //general();
    countDownLatch();
  }

  public static void general(){
    for (int i = 1; i <=6; i++) {
      new Thread(() ->{
        System.out.println(Thread.currentThread().getName()+",上完自习，离开教室");
      },String.valueOf(i)).start();
    }

    while (Thread.activeCount()>2){
      try {
        TimeUnit.SECONDS.sleep(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    System.out.println(Thread.currentThread().getName()+",班长最后离开教室并且锁门！");
  }

  public static void countDownLatch(){
    CountDownLatch countDownLatch = new CountDownLatch(6);
    for (int i = 1; i <=6; i++) {
      new Thread(() ->{
        System.out.println(Thread.currentThread().getName()+",死亡");
        //每走一个减1
        countDownLatch.countDown();
      },CountryEnum1.getCountry(i).getValue()).start();
    }
    try {
      //等待
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName()+",秦国统一！");
  }
}
