package com.uppayplugin.unionpay.javabasetest.Impl;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 17:42
 */

public class Circle extends Shape {
    private double diameter;

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }


    @Override
    public double area() {
        return Math.PI * Math.pow(getDiameter() / 2, 2);
    }

    @Override
    public double perimeter() {
        return Math.PI * getDiameter();
    }
}
