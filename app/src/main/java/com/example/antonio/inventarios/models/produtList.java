package com.example.antonio.inventarios.models;

import java.util.ArrayList;

public class produtList {
    ArrayList<OrderItem> ordersItemArray = new ArrayList<OrderItem>();
    ArrayList<Product> productArray = new ArrayList<Product>();

    public produtList(ArrayList<OrderItem> ordersItemArray, ArrayList<Product> productArray) {
        this.ordersItemArray = ordersItemArray;
        this.productArray = productArray;
    }

    public produtList() {

    }

    public ArrayList<OrderItem> getOrdersItemArray() {
        return ordersItemArray;
    }

    public void setOrdersItemArray(ArrayList<OrderItem> ordersItemArray) {
        this.ordersItemArray = ordersItemArray;
    }

    public ArrayList<Product> getProductArray() {
        return productArray;
    }

    public void setProductArray(ArrayList<Product> productArray) {
        this.productArray = productArray;
    }
}
