package com.xmj.startfromzero.designpattern.creationtype.factorymethod;


/**
 * @author XiaoMengjie
 */
public class RealComputerFactory extends ComputerFactory {
    @Override
    public <T extends Computer> T createComputer(Class<T> tClass) {
        Computer computer = null;
        try {
            computer = (Computer) Class.forName(tClass.getName()).newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (T) computer;
    }
}
