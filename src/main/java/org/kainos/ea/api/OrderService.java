package org.kainos.ea.api;

import org.kainos.ea.cli.*;
import org.kainos.ea.client.*;
import org.kainos.ea.db.OrderDao;
import org.kainos.ea.core.OrderValidator;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderValidator orderValidator = new OrderValidator();
    public List<Order> getAllOrders() throws FailedToGetOrdersException {
        try {
            List<Order> orderList = orderDao.getAllOrders();

            /*Prints all orders sorted by orderDate
            System.out.println("All orders sorted by dispatch date: ");
            orderList.stream().sorted().forEach(System.out::println);

            //Prints all orders made in the last week


            //Prints orders with a customerID of 1
            System.out.println("\nAll orders with a CustomerID of 1: ");
            orderList.stream().filter(order -> order.getCustomerID() == 1).forEach(System.out::println);

            //Prints the most recent order
            System.out.println("\nMost recent order: ");
            System.out.println(Collections.max(orderList));

            //Prints the oldest order
            System.out.println("\nOldest order: ");
            System.out.println(Collections.min(orderList));

            //Prints the total count of all orders
            System.out.println("\nTotal number of orders: ");
            System.out.println(orderList.size());

            //Map to count the number of times each CustomerID makes an order
            Map<Integer, Long> countOrderMap = orderList.stream()
                    .collect(Collectors.groupingBy(Order::getCustomerID, Collectors.counting()));

            //Prints the customerID who has the most orders
            System.out.println("Customer with most orders: "
                    + Collections.max(countOrderMap.entrySet(), Map.Entry.comparingByValue()));

            //Prints the customerID who has the least orders
            System.out.println("Customer with least orders: "
                    + Collections.min(countOrderMap.entrySet(), Map.Entry.comparingByValue()));
             */

            return orderList;
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }
    }

    public Order getOrderByID(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderDao.getOrderByID(id);

            if(order == null){
                throw new OrderDoesNotExistException();
            }

            return order;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetOrdersException();
        }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);
            if(validation != null){
                throw new InvalidOrderException(validation);
            }
            int id = orderDao.createOrder(order);

            if(id == -1){
                throw new FailedToCreateOrderException();
            }

            return id;
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }
    }

    public void updateOrder(OrderRequest order, int id) throws FailedToUpdateOrderException, OrderDoesNotExistException, InvalidOrderException {
        try{
            String validation = orderValidator.isValidOrder(order);
            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrderByID(id);

            if(orderToUpdate == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }
}
