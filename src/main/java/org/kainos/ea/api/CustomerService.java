package org.kainos.ea.api;

import org.kainos.ea.cli.Customer;
import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.client.CustomerDoesNotExistException;
import org.kainos.ea.client.FailedToCreateCustomerException;
import org.kainos.ea.client.FailedToGetCustomersException;
import org.kainos.ea.client.InvalidCustomerException;
import org.kainos.ea.core.CustomerValidator;
import org.kainos.ea.db.CustomerDao;

import java.sql.SQLException;
import java.util.*;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    private CustomerValidator customerValidator = new CustomerValidator();

    public List<Customer> getAllCustomers() throws FailedToGetCustomersException {
        try{
            List<Customer> customerList = customerDao.getAllCustomers();
            return customerList;
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetCustomersException();
        }
    }

    public Customer getCustomerByID(int id) throws CustomerDoesNotExistException, FailedToGetCustomersException {
        try {
            Customer customer = customerDao.getCustomerByID(id);
            if(customer == null){
                throw new CustomerDoesNotExistException();
            }
            return customer;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetCustomersException();
        }
    }

    public int createCustomer(CustomerRequest customer) throws FailedToCreateCustomerException, InvalidCustomerException {
        try {
            String validation = customerValidator.isValidCustomer(customer);
            if(validation != null){
                throw new InvalidCustomerException(validation);
            }
            int id = customerDao.createCustomer(customer);

            if(id == -1){
                throw new FailedToCreateCustomerException();
            }

            return id;
        } catch(SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateCustomerException();
        }
    }
}
