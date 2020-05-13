package com.xmj.startfromzero.designpattern.creationtype.factorymethod;

/**
 * @author XiaoMengjie
 */
public class Client {

    public static void main(String[] args) {
        RealComputerFactory realComputerFactory = new RealComputerFactory();
        realComputerFactory.createComputer(PhoneComputer.class).create();
    }
}
