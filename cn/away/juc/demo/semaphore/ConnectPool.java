package cn.away.juc.demo.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 数据库连接池
 *
 * @author wei.guo
 * @date 2023/3/12
 */
public class ConnectPool {

    private int size;
    private Connect[] connects;

    /** 记录对应下标的Connect是否已被使用 */
    private boolean[] connectFlag;
    /** 信号量对象 */
    private Semaphore semaphore;

    /**
     * size:初始化连接池大小
     */
    public ConnectPool(int size) {
        this.size = size;
        semaphore = new Semaphore(size, true);
        connects = new Connect[size];
        connectFlag = new boolean[size];
        initConnects(); // 初始化连接池
    }

    private void initConnects() {
        for (int i = 0; i < this.size; i++) {
            connects[i] = new Connect();
        }
    }

    /**
     * 获取数据库连接
     */
    public Connect openConnect() throws InterruptedException {
        // 得先获得使用许可证，如果信号量为0，则拿不到许可证，一直阻塞直到能获得
        semaphore.acquire();
        return getConnect();
    }

    private synchronized Connect getConnect() {
        for (int i = 0; i < connectFlag.length; i++) {
            if (!connectFlag[i]) {
                // 标记该连接已被使用
                connectFlag[i] = true;
                return connects[i];
            }
        }
        return null;
    }

    /**
     * 释放某个数据库连接
     */
    public synchronized void releaseConnect(Connect connect) {
        for (int i = 0; i < this.size; i++) {
            if (connect == connects[i]) {
                connectFlag[i] = false;
                semaphore.release();
            }
        }
    }

}
