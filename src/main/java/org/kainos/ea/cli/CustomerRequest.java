package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerRequest {
    private String name;
    private String address;
    private String phoneNum;

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

    @JsonCreator
    public CustomerRequest(
            @JsonProperty("Name") String name,
            @JsonProperty("Address")String address,
            @JsonProperty("Phone Number")String phoneNum) {
                this.name = name;
                this.address = address;
                this.phoneNum = phoneNum;
            }
}
