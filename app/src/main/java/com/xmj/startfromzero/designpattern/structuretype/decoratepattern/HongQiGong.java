package com.xmj.startfromzero.designpattern.structuretype.decoratepattern;

/**
 * @author XiaoMengjie
 */
public class HongQiGong extends Master {

    public HongQiGong(Swordsman swordsman) {
        super(swordsman);
    }

    public void teachAttackMagic(){
        System.out.println("洪七公传授打狗棒");
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();

    }
}
