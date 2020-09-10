package com.xmj.startfromzero.designpattern.structuretype.proxypattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author XiaoMengjie
 *
 * 动态代理
 */
public class DynamicAgent implements InvocationHandler {

    private Object object;

    public DynamicAgent(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(object, args);
        if (method.getName().equals("buy")){
            System.out.println("买买买");
        }
        return invoke;
    }
}
