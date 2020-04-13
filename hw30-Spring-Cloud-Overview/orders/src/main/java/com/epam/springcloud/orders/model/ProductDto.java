package com.epam.springcloud.orders.model;

public class ProductDto {
    private String name;
    private Long quantity;

    public ProductDto() {
        this.name = "";
        this.quantity = 0L;
    }

    public ProductDto(String name, Long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
