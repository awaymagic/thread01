package cn.away.tl;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：自己实现的ThreadLocal
 * 性能问题 DougLee在《并发编程实战》中为我们做过性能测试
 */
public class MyThreadLocal<T> {

    /** 存放变量副本的map容器，以Thread为键，变量副本为value */
    private Map<Thread,T> threadTMap = new HashMap<>();

    public synchronized T get(){
        return threadTMap.get(Thread.currentThread());
    }

    public synchronized void set(T t){
        threadTMap.put(Thread.currentThread(), t);
    }

}
