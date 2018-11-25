package com.example.antonio.inventarios.models;

public class User {

    private String id;
    private String name;
    private String last_name;
    private String email;
    private String company_id;
    private String type;

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

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAccount_delete() {
        return account_delete;
    }

    public void setAccount_delete(boolean account_delete) {
        this.account_delete = account_delete;
    }

    private boolean account_delete;

}
