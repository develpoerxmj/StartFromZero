package com.xmj.startfromzero.designpattern.structuretype.appearancepattern;

/**
 * @author XiaoMengjie
 */
public class ZhangWuJi {

    private ZhaoShi zhaoShi;
    private NeiGong neiGong;
    private JingMai jingMai;

    public ZhangWuJi() {
        this.zhaoShi = new ZhaoShi();
        this.neiGong = new NeiGong();
        this.jingMai = new JingMai();
    }

    public void QianKun(){
        jingMai.JingMai();
        neiGong.QianKun();
    }

    public void QiShang(){
        jingMai.JingMai();
        neiGong.JiuYang();
        zhaoShi.QiShangQuan();
    }
}
