package com.xmj.startfromzero.designpattern.structuretype.proxypattern;

import java.lang.reflect.Proxy;

/**
 * @author XiaoMengjie
 */
public class Client {
    public static void main(String[] args) {
//        IShop me = new TheShopper();
//        Proxy proxy = new Proxy(me);
//        proxy.buy();

        IShop shop = new TheShopper();
        DynamicAgent dynamicAgent = new DynamicAgent(shop);
        ClassLoader classLoader = shop.getClass().getClassLoader();
        IShop iShop = (IShop) Proxy.newProxyInstance(classLoader, new Class[]{IShop.class}, dynamicAgent);
        iShop.buy();
    }
}
