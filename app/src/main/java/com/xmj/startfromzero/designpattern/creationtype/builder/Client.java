package com.xmj.startfromzero.designpattern.creationtype.builder;

/**
 * @author XiaoMengjie
 */
public class Client {
    public static void main(String[] args) {
//        Director director = new Director();
//        director.createComputer("", "", "");
//
        Phone phone = new Phone.Builder()
                .addRam("主存").build();
        System.out.println(phone.getCpu());
        System.out.println(phone.getMainBoard());
        System.out.println(phone.getRam());
    }
}
