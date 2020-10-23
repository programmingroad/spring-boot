package com.example.designpattern.observer;

/**
 * @author: programmingroad
 * @create: 2020/02/20 16:45
 * @description:
 **/
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "
                + Integer.toOctalString(subject.getState()));
    }

}
