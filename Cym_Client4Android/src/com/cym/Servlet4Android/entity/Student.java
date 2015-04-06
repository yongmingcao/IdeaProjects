package com.cym.Servlet4Android.entity;

/**
 * Created by Administrator on 2015/4/4.
 */
public class Student {
    private String name;
    private Long id;
    private int age;
    public Student(){

    }
    public Student(Long id, String name, int age){
        this.name=name;
        this.age=age;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
