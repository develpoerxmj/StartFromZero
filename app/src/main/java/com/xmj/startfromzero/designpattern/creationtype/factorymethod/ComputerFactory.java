package com.xmj.startfromzero.designpattern.creationtype.factorymethod;

import java.util.concurrent.TimeUnit;

/**
 * @author XiaoMengjie
 */
public abstract class ComputerFactory {
    /**
     * 创建电脑
     * @param tClass
     * @param <T>
     * @return
     */
    public abstract <T extends Computer> T createComputer(Class<T> tClass);
}
