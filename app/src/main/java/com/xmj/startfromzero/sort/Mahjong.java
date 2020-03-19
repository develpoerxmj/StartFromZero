package com.xmj.startfromzero.sort;

public class Mahjong {

    //(1-3)三种花色
    private int type;
    //(1-9)九数
    private int num;

    public Mahjong(int type, int num) {
        this.type = type;
        this.num = num;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
