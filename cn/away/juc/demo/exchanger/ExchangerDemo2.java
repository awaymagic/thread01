package cn.away.juc.demo.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟对账场景
 *
 * @author wei.guo
 * @date 2023/3/18
 */
public class ExchangerDemo2 {

    private static final Exchanger<String> exchanger = new Exchanger();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {

        threadPool.execute(() -> {
            try {
                String a = "12379871924sfkhfksdhfks";
                exchanger.exchange(a);
                System.out.println("a= " + a);
            } catch (InterruptedException e) {
            }
        });

        threadPool.execute(() -> {
            try {
                String b = "32423423jknjkfsbfj";
                String a = exchanger.exchange(b);
                System.out.println("A和B数据是否一致：" + a.equals(b));
                System.out.println("a= " + a);
                System.out.println("b= " + b);
            } catch (InterruptedException e) {
            }
        });

        threadPool.shutdown();

    }

}
