package com.xmj.startfromzero.designpattern.structuretype.proxypattern;

/**
 * @author XiaoMengjie
 */
public class Proxy implements IShop {

    private IShop shop;

    public Proxy(IShop shop) {
        this.shop = shop;
    }

    @Override
    public void buy() {
        shop.buy();
    }
}
