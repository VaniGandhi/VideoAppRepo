package com.example.myapplication.Pagination;

public class Student {

    private String rollno;
    private String name;
    private int image;

    public Student(String rollno, String name , int image) {
        this.rollno=rollno;
        this.name=name;
        this.image=image;

    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
