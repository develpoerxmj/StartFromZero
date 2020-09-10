package com.xmj.startfromzero.designpattern.structuretype.proxypattern;

/**
 * @author XiaoMengjie
 */
public class TheShopper implements IShop {
    @Override
    public void buy() {
        System.out.println("购买");
    }
}
