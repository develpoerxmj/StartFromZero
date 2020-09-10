package com.xmj.startfromzero.designpattern.structuretype.xiangyuanpattern;

/**
 * @author XiaoMengjie
 */
public class Client {
    public static void main(String[] args) {
        Goods goods = GoodsFactory.getGoods("iphone7");
        goods.showGoodsPrice("32g");
        Goods goods1 = GoodsFactory.getGoods("iphone7");
        goods1.showGoodsPrice("32g");
        Goods goods2 = GoodsFactory.getGoods("iphone7");
        goods2.showGoodsPrice("128g");
    }
}
