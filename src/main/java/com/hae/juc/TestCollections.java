package com.hae.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 测试多线程情况下，集合类的安全问题
 */
public class TestCollections {
    CountDownLatch countDownLatch = new CountDownLatch(3);


    @Test
    public void testList() throws InterruptedException {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

    @Test
    public void testSynchronizedList() throws InterruptedException {
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }

    @Test
    public void testCopyOnWriteArrayList() throws InterruptedException {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
    }
}
