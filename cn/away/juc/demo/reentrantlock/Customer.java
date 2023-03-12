package cn.away.juc.demo.reentrantlock;

/**
 * @author wei.guo
 * @date 2023/3/12
 */
public class Customer implements Runnable {

    private Queue queue;

    public Customer(Queue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            // 隔2秒轮询消费一次
            while (true) {
                Thread.sleep(2000);
                System.out.println("consumer消费：" + queue.take());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
