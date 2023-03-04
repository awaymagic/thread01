package cn.away.base.vola;

import cn.away.tools.SleepTools;

/**
 * 类说明：演示Volatile的提供的可见性
 */
public class VolatileCase {

    private static boolean ready;
    // 使用 volatile 后"number = "可以打印出来值
    // private static volatile boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while(!ready){
                // 这里如果把System.out.println放开,会发现 ready 不用加 volatile 也可以打印 "number = "
                // 因为 println 里用 synchronized 实现的
                // 实现的volatile 是轻量级的锁机制 而 synchronized 是重量级的，volatile的功能 synchronized 当然有
                // System.out.println("lll");

                // 其实 sleep 也可以的
                // Thread.sleep(1000);
            };//无限循环
            System.out.println("number = " + number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new PrintThread().start();
        SleepTools.second(1);
        number = 51;
        ready = true;
        SleepTools.second(5);
        System.out.println("main is ended!");
    }
}
