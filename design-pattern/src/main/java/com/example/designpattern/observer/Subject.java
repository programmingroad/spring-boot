package com.example.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: programmingroad
 * @create: 2020/02/20 16:43
 * @description:
 **/
public class Subject {

    private List<Observer> observers
            = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
