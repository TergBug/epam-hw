package com.epam.springcloud.orders.model;

public class Order {
    private String userName;
    private String product;

    public Order(String userName, String product) {
        this.userName = userName;
        this.product = product;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}

