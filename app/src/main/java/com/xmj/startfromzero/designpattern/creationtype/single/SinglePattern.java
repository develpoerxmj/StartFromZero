package com.xmj.startfromzero.designpattern.creationtype.single;

import java.io.ObjectStreamException;

/**
 * 单例模式
 * 一个类只有一个实例，且自动实例化像整个系统提供这个实例
 * 建议使用饿汉式
 *
 * 减少内存开支，性能开销，避免对资源多重占用
 * 很难扩展
 *
 * @author XiaoMengjie
 */
public class SinglePattern {

    /**
     * 饿汉式
     * 类加载时完成初始化
     * 类加载慢，获取对象慢
     */
//    private static SinglePattern pattern = new SinglePattern();
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
     * 懒汉式（线程不安全）
     * 第一次慢，多线程不能正常工作
     */
//    private static SinglePattern pattern = null;
//
//    private SinglePattern(){};
//
//    public static SinglePattern getInstance(){
//        if (pattern == null){
//            pattern = new SinglePattern();
//        }
//        return pattern;
//    }

    /**
     * 懒汉式（线程安全）
     * 每次getInstance都会进行同步，开销大，不建议这种
     */
//    private static SinglePattern pattern;
//
//    private SinglePattern(){}
//
//    public static synchronized SinglePattern getInstance(){
//        if (pattern == null){
//            pattern = new SinglePattern();
//        }
//        return pattern;
//    }

    /**
     * 双重检查模式（DCL）
     */
//    private static volatile SinglePattern pattern = null;
//
//    private SinglePattern(){}
//
//    public static SinglePattern getInstance(){
//        //为了不必要的同步
//        if (pattern == null){
//            synchronized (SinglePattern.class){
//                //为null才创建
//                if (pattern == null){
//                    pattern = new SinglePattern();
//                }
//            }
//        }
//        return pattern;
//    }

    /**
     * 静态内部类单例
     * 推荐使用
     * 加载SinglePattern并不会初始化mInstance，只有调用getInstance才会初始化
     */
    private SinglePattern() {
    }

    public static SinglePattern getInstance(){
        return SingletonHolder.mInstance;
    }

    private static class SingletonHolder{
        private static final SinglePattern mInstance = new SinglePattern();
    }

    /**
     * 枚举单例
     */
    public enum Singleton{
        INSTANCE;
        public void doSomething(){}
    }

    /**
     * 防止反序列化
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return Singleton.INSTANCE;
    }
}
