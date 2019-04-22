package com.example.jnn.today.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jnn
 * @date 2019/4/22
 * @description 功能代理者
 */
public class CoustomProxy {
    /**
     * 通过一个Proxy工具，为委托类的接口自动生成一个代理对象，后续的函数调用都通过这个代理对象进行发起，
     * 最终会执行到InvocationHandler#invoke方法，在这个方法里除了调用真实委托类对应的方法，还可以做一些其他自定义的逻辑，
     *
     * @param object 委托对象
     * @return 代理类
     */
    public Object bind(Object object) {
        //功能提供者、功能接口、功能代理者
        Object proxyObject = Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return null;
                    }
                });
        return proxyObject;
    }
}
