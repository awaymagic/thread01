package cn.away.juc.demo.exchanger;

import java.util.concurrent.Exchanger;

/**
 * 模拟交易场景
 * 两方做交易，如果一方先到要等另一方也到了才能交易，交易就是执行exchange方法交换数据
 * @author wei.guo
 * @date 2023/3/18
 */
public class ExchangerDemo {

    private static final Exchanger EXCHANGER = new Exchanger();
    static String goods = "电脑";
    static String money = "$4000";
    public static void main(String[] args) throws InterruptedException {

        System.out.println("准备交易，一手交钱一手交货...");
        // 卖家
        new Thread(() -> {
            System.out.println("卖家到了，已经准备好货：" + goods);
            try {
                String money = (String) EXCHANGER.exchange(goods);
                System.out.println("卖家收到钱：" + money);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(3000);

        // 买家
        new Thread(() -> {
            try {
                System.out.println("买家到了，已经准备好钱：" + money);
                String goods = (String) EXCHANGER.exchange(money);
                System.out.println("买家收到货：" + goods);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

}
