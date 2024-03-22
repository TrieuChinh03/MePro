package com.example.mepro.layout_listwork.model;

public class Category {
    private int id;
    private String nameCategory;
    private int color;

    public Category(String nameCategory, int color) {
        this.nameCategory = nameCategory;
        this.color = color;
    }

    public Category(int id, String nameCategory, int color) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.color = color;
    }
    
    public int getColor() {
        return color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }
}
