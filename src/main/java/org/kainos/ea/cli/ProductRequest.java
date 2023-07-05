package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductRequest {
    private String productName;
    private String description;
    private double price;
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @JsonCreator
    public ProductRequest(
            @JsonProperty("Product_Name") String productName,
            @JsonProperty("Description") String description,
            @JsonProperty("Price") double price) {
        this.productName = productName;
        this.description = description;
        this.price = price;
    }
}
