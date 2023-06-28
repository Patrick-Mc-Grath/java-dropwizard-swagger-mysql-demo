package org.kainos.ea.db;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;

public class OrderDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Order> getAllOrders() throws SQLException {
        Connection c = databaseConnector.getConnection();
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Orders`");

            List<Order> orderList = new ArrayList<>();

            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("OrderDate")
                );

                orderList.add(order);
            }

            return orderList;
    }

    public Order getOrderByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate FROM `Orders` " +
                "WHERE OrderID = " + id);


        while (rs.next()) {
            return new Order(
                    rs.getInt("OrderID"),
                    rs.getInt("CustomerID"),
                    rs.getDate("OrderDate")
            );
        }
        return null;
    }

    public int createOrder(OrderRequest order) throws SQLException {
        try (Connection c = databaseConnector.getConnection()) {
            String insertStatement = "INSERT INTO Orders(CustomerID, ProductID, OrderDate) VALUES(?,?,?)";

            PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, order.getCustomerID());
            st.setInt(2, order.getProductID());
            st.setDate(3, order.getOrderDate());

            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return -1;
        }
    }

    public void updateOrder(int id, OrderRequest order) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE Order SET CustomerID = ?, ProductID = ?, OrderDate = ? WHERE OrderID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setInt(1, order.getCustomerID());
        st.setInt(2, order.getProductID());
        st.setDate(3, order.getOrderDate());
        st.setInt(4, id);

        st.executeUpdate();
    }
}
