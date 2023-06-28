package org.kainos.ea.core;

import org.kainos.ea.cli.OrderRequest;

import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;

public class OrderValidator {
    public String isValidOrder(OrderRequest order) {
        /*Date orderDate = convertDate(order.getOrderDate());
        Date lastYear = lastYear();
        if(orderDate.before(lastYear)){
            return "This order is over a year old.";
        }*/
        return null;
    }

    private java.util.Date convertDate(java.sql.Date orderDate) {
        java.util.Date utilDate = new java.util.Date(orderDate.getTime());
        return utilDate;
    }

    private java.util.Date lastYear(){
        LocalDate today = LocalDate.now().minusYears(1);
        Date lastYear = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return lastYear;
    }
}
