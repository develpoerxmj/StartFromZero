package com.xmj.startfromzero.designpattern.creationtype.builder;

/**
 * @author XiaoMengjie
 */
public class Phone {

    String mCpu;
    String mMainBoard;
    String mRam;

    public Phone(Builder builder) {
        mCpu = builder.mCpu;
        mMainBoard = builder.mMainBoard;
        mRam = builder.mRam;
    }

    public Phone() {
        this(new Builder());
    }

    public String getCpu() {
        return mCpu;
    }

    public void setCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    public String getMainBoard() {
        return mMainBoard;
    }

    public void setMainBoard(String mMainBoard) {
        this.mMainBoard = mMainBoard;
    }

    public String getRam() {
        return mRam;
    }

    public void setRam(String mRam) {
        this.mRam = mRam;
    }

    public static class Builder{

        String mCpu;
        String mMainBoard;
        String mRam;

        public Builder(Phone phone) {
            this.mCpu = phone.mCpu;
            this.mMainBoard = phone.mMainBoard;
            this.mRam = phone.mRam;
        }

        public Builder() {
            mCpu = "默认CPU";
            mMainBoard = "默认主板";
            mRam = "默认主存";
        }

        public Builder addCpu(String cpu){
            mCpu = cpu;
            return this;
        }

        public Builder addBoard(String board){
            mMainBoard = board;
            return this;
        }

        public Builder addRam(String ram){
            mRam = ram;
            return this;
        }

        public Phone build(){
            return new Phone(this);
        }
    }
}
