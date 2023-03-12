package cn.away.juc.demo.reentrantlock;

/**
 * @author wei.guo
 * @date 2023/3/12
 */
public class ReentrantLockDemo3 {

    public ReentrantLockDemo3() {
    }

    public static void main(String[] args) {
        // 创建队列
        Queue queue = new Queue(5);
        //启动生产者线程
        new Thread(new Producer(queue)).start();
        //启动消费者线程
        new Thread(new Customer(queue)).start();

    }

}
