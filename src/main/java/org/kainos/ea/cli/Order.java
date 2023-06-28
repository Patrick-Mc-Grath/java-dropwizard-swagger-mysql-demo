package org.kainos.ea.cli;
import java.sql.Date;

public class Order implements Comparable<Order>{
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    private int orderID;

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    private int customerID;

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Order(int orderID, int customerID, Date orderDate) {
        setOrderID(orderID);
        setCustomerID(customerID);
        setOrderDate(orderDate);
    }

    private Date orderDate;

    @Override
    public int compareTo(Order order) {
        return this.getOrderDate().compareTo(order.getOrderDate());
    }

    @Override
    public String toString() {
        return "Order ID: " + this.getOrderID() + ", Customer ID: " + this.getCustomerID() + ", Order date: " + this.getOrderDate();
    }

}
