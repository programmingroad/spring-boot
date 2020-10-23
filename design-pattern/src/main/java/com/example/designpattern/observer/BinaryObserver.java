package com.example.designpattern.observer;

/**
 * @author: programmingroad
 * @create: 2020/02/20 16:45
 * @description:
 **/
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "
                + Integer.toBinaryString(subject.getState()));
    }
}
