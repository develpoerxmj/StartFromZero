package com.xmj.startfromzero.designpattern;

/**
 * 单例模式
 * 一个类只有一个实例，且自动实例化像整个系统提供这个实例
 * 建议使用饿汉式
 *
 * 减少内存开支，性能开销，避免对资源多重占用
 * 很难扩展
 */
public class SinglePattern {


//    /**
//     * 懒汉式
//     */
//    private static SinglePattern pattern = null;
//
//    private SinglePattern(){};
//
//    public synchronized static SinglePattern getInstance(){
//        if (pattern == null){
//            pattern = new SinglePattern();
//        }
//        return pattern;
//    }


//    /**
//     * 饿汉式
//     */
//    private static final SinglePattern pattern = new SinglePattern();
//
//    //限制产生多个对象
//    private SinglePattern() {
//    }
//
//    //通过该方法获取实例对象
//    public static SinglePattern getInstance(){
//        return pattern;
//    }

    /**
     * 双锁
     */
    private static volatile SinglePattern pattern = null;

    private SinglePattern(){}

    public static SinglePattern getInstance(){
        if (pattern == null){//多线程避免已有对象还需等待
            synchronized (SinglePattern.class){
                if (pattern == null){//避免多线程重复创建对象
                    pattern = new SinglePattern();
                }
            }
        }
        return pattern;
    }

    //类中其他方法，尽量static
}
