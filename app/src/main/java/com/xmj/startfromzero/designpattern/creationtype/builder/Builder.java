package com.xmj.startfromzero.designpattern.creationtype.builder;

/**
 * @author XiaoMengjie
 */
public abstract class Builder {
    /**
     * 设置Cpu
     * @param cpu
     */
    public abstract void buildCpu(String cpu);

    /**
     * 设置board
     * @param board
     */
    public abstract void buildMainBoard(String board);

    /**
     * 设置Ram
     * @param ram
     */
    public abstract void buildRam(String ram);

    /**
     * 创建Computer
     * @return
     */
    public abstract Computer build();
}
