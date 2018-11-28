package com.example.antonio.inventarios.models;

public class Product {

    private String name;
    private int qty;
    private String meta_info;
    private String desc;
    private String img;
    private int price;
    private String warehouse_id;
    private String company_id;

    public Product(String name, int qty, String meta_info, String desc, String img, int price, String warehouse_id, String company_id) {
        this.name = name;
        this.qty = qty;
        this.meta_info = meta_info;
        this.desc = desc;
        this.img = img;
        this.price = price;
        this.warehouse_id = warehouse_id;
        this.company_id = company_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getMeta_info() {
        return meta_info;
    }

    public void setMeta_info(String meta_info) {
        this.meta_info = meta_info;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }
}
