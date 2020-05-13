package com.xmj.startfromzero.designpattern.creationtype.simplefactory;

/**
 * @author XiaoMengjie
 */
public class AsComputer extends Computer{
    @Override
    public void create() {
        System.out.println("华硕计算机");
    }
}
