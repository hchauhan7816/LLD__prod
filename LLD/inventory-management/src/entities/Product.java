package entities;

import enums.ProductCategoryEnum;

public class Product {
    private String name;
    private double price;
    private ProductCategoryEnum category;

    public Product(String name, double price, ProductCategoryEnum category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public ProductCategoryEnum getCategory() {
        return category;
    }
}