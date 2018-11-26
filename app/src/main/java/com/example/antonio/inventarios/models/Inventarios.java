package com.example.antonio.inventarios.models;

public class Inventarios {

    private String id;
    private String dir;
    private String city;
    private int num;
    private double lat;
    private double lon;
    private String companyId;

    public Inventarios(){

    }

    public Inventarios( String dir, String city, int num, double lat, double lon, String companyId) {
        this.dir = dir;
        this.city = city;
        this.num = num;
        this.lat = lat;
        this.lon = lon;
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
