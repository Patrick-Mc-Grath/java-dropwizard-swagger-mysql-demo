package org.kainos.ea.cli;

public class Product implements Comparable<Product>{
    private int productID;
    private String ProductName;
    private String Description;

    public Product(int productID, String productName, String description, double price) {
        setProductID(productID);
        setProductName(productName);
        setDescription(description);
        setPrice(price);
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        this.ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    @Override
    public int compareTo(Product product) {
        return Double.compare(this.getPrice(), product.getPrice());
    }

    @Override
    public String toString() {
        return "Product name: " + this.getProductName() + ", Product price: " + this.getPrice();
    }
}
