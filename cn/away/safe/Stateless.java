package cn.away.safe;

/**
 * @author Mark老师   图灵学院 https://www.tulingxueyuan.cn/
 * 类说明：无状态的类
 */
public class Stateless {

    // 被 final 修饰
    final int i = 1;
    // 被 final 修饰的对象.表示引用不可变
    final Object x = new Object();

    public int getI() {
        return i;
    }

    /**
     * 只有方法时被称为无状态的类 一定是线程安全的
     */
   public void add(int x, int y ){
       // i++;
   }

    /**
     * 也是线程安全的
     */
    public void add(Object x) {
        // i++;
    }

}
