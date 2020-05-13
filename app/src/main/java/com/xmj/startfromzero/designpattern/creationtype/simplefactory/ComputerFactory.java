package com.xmj.startfromzero.designpattern.creationtype.simplefactory;

/**
 * @author XiaoMengjie
 */
public class ComputerFactory {
    public static Computer createComputer(String type){
        Computer computer = null;
        switch (type){
            case "Leno":
                computer = new LenoComputer();
                break;
            case "Hp":
                computer = new HpComputer();
                break;
            case "As":
                computer = new AsComputer();
                break;
            default:
                break;
        }
        return computer;
    }
}
