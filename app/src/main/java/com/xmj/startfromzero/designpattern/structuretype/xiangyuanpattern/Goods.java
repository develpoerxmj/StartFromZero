package com.xmj.startfromzero.designpattern.structuretype.xiangyuanpattern;

public class Goods implements IGoods {

    private String name;
    private String version;

    public Goods(String name) {
        this.name = name;
    }

    @Override
    public void showGoodsPrice(String version) {
        if (version.equalsIgnoreCase("32G")){
            System.out.println("价格为5199元");
        }else if (version.equalsIgnoreCase("128G")){
            System.out.println("价格为5999元");
        }
    }
}
