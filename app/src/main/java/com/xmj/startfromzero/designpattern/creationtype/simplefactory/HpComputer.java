package com.xmj.startfromzero.designpattern.creationtype.simplefactory;

/**
 * @author XiaoMengjie
 */
public class HpComputer extends Computer{
    @Override
    public void create() {
        System.out.println("惠普计算机");
    }
}
