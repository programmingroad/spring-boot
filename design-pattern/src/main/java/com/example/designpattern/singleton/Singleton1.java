package com.example.designpattern.singleton;

/**
 * @author: programmingroad
 * @create: 2020/01/04 20:42
 * @description: 懒汉式 线程不安全
 * 这种方式是最基本的实现方式，这种实现最大的问题就是不支持多线程。因为没有加锁 synchronized，所以严格意义上它并不算单例模式。
 * 这种方式 lazy loading 很明显，不要求线程安全，在多线程不能正常工作。
 **/
public class Singleton1 {

    private static Singleton1 instance;

    /**
     * 构造函数私有化 防止外部创建对象
     */
    private Singleton1() {
    }

    /**
     * 静态方法
     *
     * @return
     */
    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
