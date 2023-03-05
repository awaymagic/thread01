package cn.away.base.wn;

import cn.away.tools.SleepTools;

/**
 * 类说明：测试 wait/notify/notifyAll
 */
public class TestWN {

    private static Express express = new Express(0,"WUHAN");

    /** 检查里程数变化的线程,不满足条件，线程一直等待 */
    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /** 检查地点变化的线程,不满足条件，线程一直等待 */
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2; i++) {
            new CheckSite().start();
        }
        for (int i = 0; i < 2; i++) {
            new CheckKm().start();
        }
        SleepTools.ms(500);

        for (int i = 0; i < 5; i++) {
            synchronized (express) {
                express.change();
                // express.notify();
                // notifyAll 会唤醒全部的 wait 线程，notify 只会唤醒一个
                express.notifyAll();
            }
            SleepTools.ms(500);
        }
    }
}
