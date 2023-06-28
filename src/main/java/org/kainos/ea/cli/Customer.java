package org.kainos.ea.cli;

public class Customer {
    private int CustomerID;
    private String name;
    private String address;
    private String phoneNum;

    public Customer(int customerID, String name, String address, String phoneNum) {
        setCustomerID(customerID);
        setName(name);
        setAddress(address);
        setPhoneNum(phoneNum);
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }



}
