package com.xmj.startfromzero.designpattern.structuretype.decoratepattern;

/**
 * @author XiaoMengjie
 */
public abstract class Master extends Swordsman{

    private Swordsman swordsman;

    public Master(Swordsman swordsman) {
        this.swordsman = swordsman;
    }

    @Override
    public void attackMagic() {
        swordsman.attackMagic();
    }
}
