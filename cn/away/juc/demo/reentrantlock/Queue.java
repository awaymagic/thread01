package cn.away.juc.demo.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 队列封装类
 * @author wei.guo
 * @date 2023/3/12
 */
public class Queue {

    private Object[] items ;
    int size = 0;
    int takeIndex;
    int putIndex;
    private ReentrantLock lock;
    /**
     * 消费者线程阻塞唤醒条件，队列为空阻塞，生产者生产完唤醒
     */
    public Condition notEmpty;
    /**
     * 生产者线程阻塞唤醒条件，队列满了阻塞，消费者消费完唤醒
     */
    public Condition notFull;

    public Queue(int capacity){
        this.items = new Object[capacity];
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }


    public void put(Object value) throws Exception {
        //加锁
        lock.lock();
        try {
            while (size == items.length)
                // 队列满了让生产者等待
            {
                notFull.await();
            }

            items[putIndex] = value;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            size++;
            notEmpty.signal(); // 生产完唤醒消费者

        } finally {
            System.out.println("producer生产：" + value);
            //解锁
            lock.unlock();
        }
    }

    public Object take() throws Exception {
        lock.lock();
        try {
            // 队列空了就让消费者等待
            while (size == 0) {
                notEmpty.await();
            }
            // 环形数组
            Object value = items[takeIndex];
            items[takeIndex] = null;
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            size--;
            notFull.signal(); // 消费完唤醒生产者生产
            return value;
        } finally {
            lock.unlock();
        }
    }
}
