package com.example.designpattern.factory;

/**
 * @author: programmingroad
 * @create: 2020/01/06 22:27
 * @description:
 **/
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
