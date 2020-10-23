package com.example.designpattern.factory;

/**
 * @author: programmingroad
 * @create: 2020/01/06 22:22
 * @description:
 **/

public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
