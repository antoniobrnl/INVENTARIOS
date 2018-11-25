package com.example.antonio.inventarios.models;

public class Company {
    private String id;
    private String name;
    private String dir;
    private String img;
    private int num;
    private String schedule;
    private String id_warehouse;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getId_warehouse() {
        return id_warehouse;
    }

    public void setId_warehouse(String id_warehouse) {
        this.id_warehouse = id_warehouse;
    }
}
