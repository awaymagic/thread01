package cn.away.juc.demo.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * (递归调用)
 *
 * @author wei.guo
 * @date 2023/3/12
 */
public class Counter {

    /**
     * 创建 ReentrantLock 对象
     */
    private final ReentrantLock lock = new ReentrantLock();

    public void recursiveCall(int num) {
        lock.lock(); // 获取锁
        try {
            if (num == 0) {
                return;
            }
            System.out.println("执行递归，num = " + num);
            recursiveCall(num - 1);
        } finally {
            lock.unlock(); // 释放锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建计数器对象
        Counter counter = new Counter();
        // 测试递归调用
        counter.recursiveCall(10);
    }

}
