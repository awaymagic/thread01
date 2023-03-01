package cn.away.base.wn;

/**
 *类说明：快递实体类
 */
public class Express {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm(){
        this.km = 101;
        notify();
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public  synchronized  void changeSite(){
        this.site = "BeiJing";
        notifyAll();
    }

    /*线程等待公里的变化*/
    public synchronized void waitKm(){
        while(this.km<100){
            try {
                wait();
                System.out.println("Check Site thread["
                                +Thread.currentThread().getId()
                        +"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Km is "+this.km+",I will change db");
    }

    /*线程等待目的地的变化*/
    public synchronized void waitSite(){
        while(this.site.equals(CITY)){//快递到达目的地
            try {
                wait();
                System.out.println("Check Site thread["+Thread.currentThread().getId()
                		+"] is be notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the site is "+this.site+",I will call user");
    }
}
