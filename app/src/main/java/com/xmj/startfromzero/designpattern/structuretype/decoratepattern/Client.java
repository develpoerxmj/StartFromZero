package com.xmj.startfromzero.designpattern.structuretype.decoratepattern;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author XiaoMengjie
 */
public class Client {
    public static void main(String[] args) {
        YangGuo guo = new YangGuo();
//        guo.attackMagic();
        HongQiGong gong = new HongQiGong(guo);
//        gong.attackMagic();
        OuTangFeng fang = new OuTangFeng(gong);
        fang.attackMagic();

    }
}
