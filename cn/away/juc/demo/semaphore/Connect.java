package cn.away.juc.demo.semaphore;

/**
 * 数据库连接
 *
 * @author wei.guo
 * @date 2023/3/12
 */
public class Connect {

    private static int count = 1;
    private int id = count++;

    public Connect() {
        // 假设打开一个连接很耗费资源，需要等待1秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("连接#" + id + "#已与数据库建立通道！");
    }

    @Override
    public String toString() {
        return "#" + id + "#";
    }

}
