package com.example.kfc;

public class MenuDataModel {
    String name;
    int image;
    String dbName;

    public MenuDataModel() {
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public MenuDataModel(String name, int image, String dbName) {
        this.name = name;
        this.image = image;
        this.dbName=dbName;
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
