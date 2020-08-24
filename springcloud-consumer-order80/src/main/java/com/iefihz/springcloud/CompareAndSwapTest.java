package com.iefihz.springcloud;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 分别使用synchronized、CAS+自旋锁、CAS+自旋锁+版本号处理并发问题
 *
 * @author He Zhifei
 * @date 2020/7/11 17:27
 */
public class CompareAndSwapTest {

    private static int m = 0;

    private static int n = 0;

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    // 可以把stamp理解为版本
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(0, 0);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i ++) {
            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Thread.sleep(new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int j = 0; j < 100; j ++) {

                        m ++;


                        synchronized (CompareAndSwapTest.class) {
                            n ++;
                        }


                        // CAS + 自旋锁
                        while (true) {
                            int current = atomicInteger.get();
                            int next = current + 1;
                            if (atomicInteger.compareAndSet(current, next)) break;
                        }
                        // 这个方法就是使用了CAS+自旋锁实现的
//                        atomicInteger.incrementAndGet();


                        // 解决CAS ABA的问题
                        while (true) {

                            //1. 获取stamp
                            int expectedStamp = atomicStampedReference.getStamp();

                            /**
                             * 2.
                             * 这两个不能用int接收，因为x.compareAndSet接收的reference是引用类型，
                             * 如果传int，则会先调用Integer.valueOf把int转Integer，而这个转换过程中，
                             * -128-127之间是有缓存的，也就是使用的是跟原来同一个对象。而超过这个
                             * 范围后，会重新new一个Integer，导致 current != expectedReference
                             */
                            Integer expectedReference = atomicStampedReference.getReference();
                            Integer newReference = expectedReference +1;

                            //3. expectedStamp + 1
                            int newStamp = expectedStamp + 1;

                            if (atomicStampedReference.compareAndSet(expectedReference, newReference, expectedStamp, newStamp)) break;
                        }
                    }
                }
            }).start();
        }

        Thread.sleep(2000);

        System.out.println("不做任何处理出现并发问题的结果m: " + m);
        System.out.println("使用synchronized处理并发的结果n: " + n);
        System.out.println("使用CAS+自旋锁处理并发的结果: " + atomicInteger.get());
        System.out.println("使用CAS+自旋锁+版本号处理并发ABA问题的结果Reference: " + atomicStampedReference.getReference());
        System.out.println("使用CAS+自旋锁+版本号处理并发ABA问题的版本号stamp: " + atomicStampedReference.getStamp());
    }
}
