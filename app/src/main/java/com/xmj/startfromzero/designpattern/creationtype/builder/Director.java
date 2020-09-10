package com.xmj.startfromzero.designpattern.creationtype.builder;

/**
 * @author XiaoMengjie
 */
public class Director {

    Builder builder = null;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Director() {
        if (builder == null){
            builder = new ConcreteBuilder();
        }
    }

    public Computer createComputer(String cpu, String board, String ram){
        builder.buildCpu(cpu);
        builder.buildMainBoard(board);
        builder.buildRam(ram);
        return builder.build();
    }
}
