package com.example.designpattern.factory;

/**
 * @author: programmingroad
 * @create: 2020/01/06 22:28
 * @description:
 **/
public class ShapeFactory {

    //使用 getShape 方法获取形状类型的对象
    public Shape getShape(ShapeEnum shapeEnum) {
        switch (shapeEnum) {
            case CIRCLE:
                return new Circle();
            case RECTANGLE:
                return new Rectangle();
            case SQUARE:
                return new Square();
            default:
                return null;
        }
    }
}
