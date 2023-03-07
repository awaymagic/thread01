package cn.away.tl;

import cn.away.tools.SleepTools;

import java.util.Random;

/**
 * 类说明：
 */
public class ThreadLocalUnsafe implements Runnable {

    // public static Number number = new Number(0);

    /**
     * ThreadLocal 的初始化值方法 initialValue()
     * 保证每次都会产生一个新的
     * 这里使用 static 修饰是为了发生内存泄漏的时候 ThreadLocal 有强指向引用
     */
    public static ThreadLocal<Number> value = ThreadLocal.withInitial(() -> new Number(0));

    @Override
    public void run() {
        Random r = new Random();
        Number number = value.get();
        // 每个线程计数加随机数
        number.setNum(number.getNum() + r.nextInt(100));
        // 将其存储到ThreadLocal中
        value.set(number);
        SleepTools.ms(2);
        // 输出num值
        System.out.println(Thread.currentThread().getName() + "=" + value.get().getNum());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafe()).start();
        }
    }

    private static class Number {
        public Number(int num) {
            this.num = num;
        }

        private int num;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Number [num=" + num + "]";
        }
    }

}
