package com.example.jnn.today.proxy;

/**
 * @author jnn
 * @date 2019/4/22
 * @description 功能提供者
 */
public class Custom implements ICoustom {
    @Override
    public void eat() {
        System.out.print("今天吃什么");
    }
}
