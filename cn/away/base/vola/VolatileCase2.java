package cn.away.base.vola;

import cn.away.tools.SleepTools;

/**
 * 类说明：演示Volatile的提供的可见性
 */
public class VolatileCase2 {
    private static boolean ready=false;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running.......");
            while(!ready);
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new PrintThread().start();
        SleepTools.second(2);
        ready = true;
        System.out.println("cccccccc");
    }
}
