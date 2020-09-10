package com.xmj.startfromzero.designpattern.creationtype.builder;

/**
 * @author XiaoMengjie
 */
public class Computer {
    private String mCpu;
    private String mMainBoard;
    private String mRam;

    public void setCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    public void setMainBoard(String mMainBoard) {
        this.mMainBoard = mMainBoard;
    }

    public void setRam(String mRam) {
        this.mRam = mRam;
    }
}
