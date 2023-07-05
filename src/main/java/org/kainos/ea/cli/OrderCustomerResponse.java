package org.kainos.ea.cli;

import java.sql.Date;
public class OrderCustomerResponse {
    private int orderID;
    private String name;
    private int productID;
    private Date orderDate;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderCustomerResponse(int orderID, int productID, String name, Date orderDate)
    {
        setOrderID(orderID);
        setProductID(productID);
        setName(name);
        setOrderDate(orderDate);
    }
}
