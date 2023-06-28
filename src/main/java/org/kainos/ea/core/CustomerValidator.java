package org.kainos.ea.core;

import org.kainos.ea.cli.CustomerRequest;

public class CustomerValidator {
    public String isValidCustomer(CustomerRequest customer){
        if(customer.getName().length() > 50){
            return "Name greater than 50 characters";
        }
        if(customer.getAddress().length() > 200){
            return "Address greater than 200 characters";
        }
        if(customer.getPhoneNum().length() > 20){
            return "Phone Number greater than 20 characters";
        }

        return null;
    }
}
