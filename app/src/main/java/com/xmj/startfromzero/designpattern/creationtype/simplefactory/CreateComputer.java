package com.xmj.startfromzero.designpattern.creationtype.simplefactory;

/**
 * @author XiaoMengjie
 */
public class CreateComputer {
    public static void main(String[] args) {
        ComputerFactory.createComputer("Leno").create();
        ComputerFactory.createComputer("Hp").create();
        ComputerFactory.createComputer("As").create();
    }
}
