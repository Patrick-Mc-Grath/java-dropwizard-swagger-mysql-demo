package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class OrderRequest {
    private int customerID;
    private int productID;
    private Date orderDate;

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date dispatchDate) {
        this.orderDate = orderDate;
    }

    @JsonCreator
    public OrderRequest(
            @JsonProperty("CustomerID") int customerID,
            @JsonProperty("ProductID") int productID,
            @JsonProperty("OrderDate") Date orderDate) {
        this.customerID = customerID;
        this.productID = productID;
        this.orderDate = orderDate;
    }
}
