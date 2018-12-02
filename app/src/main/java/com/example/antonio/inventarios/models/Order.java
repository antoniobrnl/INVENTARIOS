package com.example.antonio.inventarios.models;

public class Order {
    private Long Date;
    private  String employee_id;
    private Long date_delivery;
    private String company_id;
    private int completed;
    private String id;

    public Order(String id, Long date, String employee_id, Long date_delivery, String company_id, int completed) {
        this.id=id;
        Date = date;
        this.employee_id = employee_id;
        this.date_delivery = date_delivery;
        this.company_id = company_id;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public Long getDate_delivery() {
        return date_delivery;
    }

    public void setDate_delivery(Long date_delivery) {
        this.date_delivery = date_delivery;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
