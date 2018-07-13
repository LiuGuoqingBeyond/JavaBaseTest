package com.uppayplugin.unionpay.javabasetest.entity.response;

/**
 * User: LiuGq
 * Date: 2018/4/12
 * Time: 15:19
 */

public class Test {
    private String one;
    private String two;
    private String three;

    @Override
    public String toString() {
        return "Test{" +
                "one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                '}';
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }
}
