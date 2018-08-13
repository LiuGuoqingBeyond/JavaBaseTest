package com.uppayplugin.unionpay.javabasetes.Impl;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 17:41
 */

public class Triangle extends Shape{

    private double a,b,c;

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }




    @Override
    public double area() {
        double p = (getA() + getB() + getC()) / 2;
        return Math.sqrt(p * (p - getA()) * (p - getB()) * (p - getC()));
    }

    @Override
    public double perimeter() {
        return getA() + getB() + getC();
    }
}
