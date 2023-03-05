package cn.away.base.pool;

import cn.away.tools.SleepTools;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 类说明：连接池的实现
 * 比如有10个线程需要获取数据库链接，但连接池只有5个大小，则其中5个线程需要等待其他的链接释放后获取，此时使用等待通知
 * 等待时候不会无限期的等待，需要设置超时时间，逾期则不等待(链接超时)
 */
public class DBPool {

    /** 容器，LinkedList 存放连接 */
    private static LinkedList<Connection> pool = new LinkedList<>();

    /** 限制了池的大小=20 */
    public DBPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    /**
     * 存放
     * 释放连接,通知其他的等待连接的线程
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool){
                // 存放链接
                pool.addLast(connection);
                // 通知其他等待连接的线程
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取
     * 在mills内无法获取到连接，将会返回null 1S
     */
    public Connection fetchConnection(long mills)
            throws InterruptedException {
        synchronized (pool){
            // 永不超时(mills 为负数)
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else { // 计算超时时间
                /* 超时时刻 */
                // 10:15 5min -> 10:20
                long future = System.currentTimeMillis() + mills;
                /* 等待时长 */
                // 5min
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    /* 唤醒一次，重新计算等待时长 */
                    // eg:4min
                    remaining = future - System.currentTimeMillis();
                }
                Connection connection = null;
                if (!pool.isEmpty()) {
                    connection = pool.removeFirst();
                }
                return connection;
            }
        }

    }
}
