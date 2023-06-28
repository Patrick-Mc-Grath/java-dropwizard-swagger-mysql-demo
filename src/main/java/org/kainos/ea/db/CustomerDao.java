package org.kainos.ea.db;

import org.kainos.ea.cli.Customer;
import org.kainos.ea.cli.CustomerRequest;
import org.kainos.ea.cli.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Customer> getAllCustomers() throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CustomerID, Name, Address, Phone FROM `Customer`");

        List<Customer> customerList = new ArrayList<>();

        while (rs.next()) {
            Customer customer = new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            );

            customerList.add(customer);
        }

        return customerList;
    }

    public Customer getCustomerByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CustomerID, Name, Address, Phone FROM `Customer` " +
                "WHERE CustomerID = " + id);

        List<Customer> customerList = new ArrayList<>();

        while (rs.next()) {
            return new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            );
        }

        return null;
    }

    public int createCustomer(CustomerRequest customer) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO `Customer`(Name, Address, Phone) VALUES (?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, customer.getName());
        st.setString(2, customer.getAddress());
        st.setString(3, customer.getPhoneNum());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()){
            return rs.getInt(1);
        }

        return -1;
    }
}
