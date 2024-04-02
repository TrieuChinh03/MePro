package com.example.mepro.layout_saving.model;

public class Category {
    private int id;
    private String name;
    private int icon;
    private int type;

    public Category(String name, int icon, int type) {
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public Category(int id, String name, int icon, int type) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        type = type;
    }
}
