package org.kainos.ea.cli;
import java.sql.Date;
import java.util.List;

public class OrderResponse {
    private int orderID;
    private String customerName;
    private List<String> productList;
    private Date orderDate;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getProductName() {
        return productList;
    }

    public void setProductList(List<String> productList) {
        this.productList = productList;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderResponse(int orderID, String customerName, List<String> productList, Date orderDate) {
        setOrderID(orderID);
        setCustomerName(customerName);
        setOrderDate(orderDate);
        setProductList(productList);
    }
}
