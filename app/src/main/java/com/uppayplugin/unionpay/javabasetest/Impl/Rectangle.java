package com.uppayplugin.unionpay.javabasetest.Impl;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 17:39
 * 矩形
 */

public class Rectangle extends Shape{
    private double length;
    private double width;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }


    @Override
    public double area() {
        return getLength() * getWidth();
    }

    @Override
    public double perimeter() {
        return 2 * (getWidth() + getWidth());
    }
}
