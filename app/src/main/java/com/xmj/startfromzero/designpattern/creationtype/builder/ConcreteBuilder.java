package com.xmj.startfromzero.designpattern.creationtype.builder;

/**
 * @author XiaoMengjie
 */
public class ConcreteBuilder extends Builder {

    private Computer computer = new Computer();

    @Override
    public void buildCpu(String cpu) {
        computer.setCpu(cpu);
    }

    @Override
    public void buildMainBoard(String board) {
        computer.setMainBoard(board);
    }

    @Override
    public void buildRam(String ram) {
        computer.setRam(ram);
    }

    @Override
    public Computer build() {
        return computer;
    }
}
