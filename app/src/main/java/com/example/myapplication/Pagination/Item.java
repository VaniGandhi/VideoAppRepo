package com.example.myapplication.Pagination;

public class Item {
    String name;
    int Length;

    public Item(String name, int length) {
        this.name = name;
        Length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return Length;
    }

    public void setLength(int length) {
        Length = length;
    }
}
