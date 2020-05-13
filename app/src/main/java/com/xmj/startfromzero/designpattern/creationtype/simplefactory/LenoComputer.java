package com.xmj.startfromzero.designpattern.creationtype.simplefactory;

/**
 * @author XiaoMengjie
 */
public class LenoComputer extends Computer{
    @Override
    public void create() {
        System.out.println("联想计算机");
    }
}
