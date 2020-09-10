package com.xmj.startfromzero.designpattern.structuretype.decoratepattern;

/**
 * @author XiaoMengjie
 */
public class OuTangFeng extends Master {

    public OuTangFeng(Swordsman swordsman) {
        super(swordsman);
    }

    public void teachAttackMagic(){
        System.out.println("欧阳锋传授打狗棒");
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();

    }
}
