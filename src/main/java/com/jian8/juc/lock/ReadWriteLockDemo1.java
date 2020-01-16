package com.jian8.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author yanglin
 * @create 2020-01-15 14:48
 */
public class ReadWriteLockDemo1 {
  public static void main(String[] args) {
    MapCache mapCache = new MapCache();
    for (int i = 1; i <=5; i++) {
      final int target = i;
      new Thread(() -> {
        mapCache.put(target+"", target+"");
      },"Thread"+i).start();
    }

    for (int i = 1; i <=5; i++) {
      final int target = i;
      new Thread(() -> {
        mapCache.get(target+"");
      },"Thread"+i).start();
    }
  }
}


/**
 * Map缓存
 */
class MapCache{
  private volatile Map<String,Object> map = new HashMap<>();
  ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

  public void put(String key,Object value){
    reentrantReadWriteLock.writeLock().lock();

    try {
      System.out.println(Thread.currentThread().getName()+",正在写入,"+key);
      map.put(key, value);
      TimeUnit.MILLISECONDS.sleep(300);
      System.out.println(Thread.currentThread().getName()+",写入成功,"+key);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      reentrantReadWriteLock.writeLock().unlock();
    }
  }

  public void get(String key){
    reentrantReadWriteLock.readLock().lock();

    try {
      System.out.println(Thread.currentThread().getName()+",正在读取,");
      Object value = map.get(key);
      TimeUnit.MILLISECONDS.sleep(300);
      System.out.println(Thread.currentThread().getName()+",读取成功,"+value);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      reentrantReadWriteLock.readLock().unlock();
    }
  }
}