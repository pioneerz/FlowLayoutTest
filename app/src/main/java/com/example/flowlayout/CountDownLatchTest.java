package com.example.flowlayout;

import java.util.concurrent.CountDownLatch;

/**
 * @Author timcoder
 * @Date 2019-09-20
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(3);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread main A start:" + countDownLatch.getCount());
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread main A end:" + countDownLatch.getCount());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread main B start:" + countDownLatch.getCount());
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread main B end:" + countDownLatch.getCount());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
                System.out.println("Thread A:" + countDownLatch.getCount());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
                System.out.println("Thread B:" + countDownLatch.getCount());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                countDownLatch.countDown();
                System.out.println("Thread C:" + countDownLatch.getCount());
            }
        }).start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                countDownLatch.countDown();
//                System.out.println("Thread D:"+countDownLatch.getCount());
//            }
//        }).start();
    }

}
