package com.example.leetcode.concurrency.alternate.print;

/**
 * @author: liubq
 * @create: 2020/11/06 11:48
 * @description:
 **/
public class Main {

    public static void main(String[] args) {
        FooBar fooBar = new FooBar(1);
        new Thread(() -> fooBar.foo(() -> System.out.print("foo")), "a").start();
        new Thread(() -> fooBar.foo(() -> System.out.print("bar")), "b").start();
    }

}

class FooBar {
    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
        }
    }

    public void bar(Runnable printBar) {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }
}
