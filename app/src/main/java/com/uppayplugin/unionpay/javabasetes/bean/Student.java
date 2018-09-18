package com.uppayplugin.unionpay.javabasetes.bean;

/**
 * User: LiuGuoqing
 * Data: 2018/9/18 0018.
 */

public class Student {
    private String name;
    private String id;
    private String value;
    private String option;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", value='" + value + '\'' +
                ", option='" + option + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
