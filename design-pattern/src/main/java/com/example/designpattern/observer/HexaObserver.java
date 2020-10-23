package com.example.designpattern.observer;

/**
 * @author: programmingroad
 * @create: 2020/02/20 16:46
 * @description:
 **/
public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: "
                + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
