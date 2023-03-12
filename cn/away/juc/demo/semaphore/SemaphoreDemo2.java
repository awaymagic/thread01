package cn.away.juc.demo.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Semaphore实现数据库连接池
 *
 * @author wei.guo
 * @date 2023/3/12
 */
public class SemaphoreDemo2 {

    public static void main(String[] args) {

        final ConnectPool pool = new ConnectPool(2);
        ExecutorService executorService = Executors.newCachedThreadPool();

        //5个线程并发来争抢连接资源
        for (int i = 0; i < 5; i++) {
            final int id = i + 1;
            executorService.execute(() -> {
                Connect connect = null;
                try {
                    System.out.println("线程" + id + "等待获取数据库连接");
                    connect = pool.openConnect();
                    System.out.println("线程" + id + "已拿到数据库连接:" + connect);
                    // 进行数据库操作2秒...然后释放连接
                    Thread.sleep(2000);
                    System.out.println("线程" + id + "释放数据库连接:" + connect);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pool.releaseConnect(connect);
                }
            });
        }
    }

}
