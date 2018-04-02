package com.impaqgroup.pos.model;

public class Product {
    private String barCode;
    private String name;
    private Double price;

    public Product(String barCode, String name, Double price) {
        this.barCode = barCode;
        this.name = name;
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
