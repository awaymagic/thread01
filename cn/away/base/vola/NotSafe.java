package cn.away.base.vola;

/**
 * 类说明：volatile 并不能能保证数据在多个线程下同时写时的线程安全
 *        volatile 最适用的场景:一个线程写，多个线程读
 */
public class NotSafe {

    private volatile long count =0;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    // count进行累加
    public void incCount(){
        count++;
    }

    // 线程
    private static class Count extends Thread{

        private NotSafe simplOper;

        public Count(NotSafe simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                simplOper.incCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NotSafe simplOper = new NotSafe();
        //启动两个线程
        Count count1 = new Count(simplOper);
        Count count2 = new Count(simplOper);
        count1.start();
        count2.start();
        Thread.sleep(50);
        System.out.println(simplOper.count);//20000?
    }
}
