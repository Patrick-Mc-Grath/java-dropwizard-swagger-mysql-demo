package org.kainos.ea.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.kainos.ea.cli.*;

public class OrderDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<OrderResponse> getAllOrders() throws SQLException {
        Connection c = databaseConnector.getConnection();
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT OrderID, Name, OrderDate FROM `Orders` INNER JOIN Customer" +
                    " USING(CustomerID)");

            List<OrderResponse> orderList = new ArrayList<>();
            List<String> emptyList = new ArrayList<>();

            while (rs.next()) {
                OrderResponse order = new OrderResponse(
                        rs.getInt("OrderID"),
                        rs.getString("Name"),
                        emptyList,
                        rs.getDate("OrderDate")
                );

                orderList.add(order);
            }

            return orderList;
    }

    public OrderResponse getOrderByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet productQuery = st.executeQuery("SELECT Product_Name FROM OrderProducts INNER JOIN Product" +
                " ON OrderProducts.ProductID = Product.Product_ID WHERE OrderID = " + id + ";");

        List<String> productList = new ArrayList<>();

        while(productQuery.next()) {
            productList.add(productQuery.getString("Product_Name"));
        }

        ResultSet rs = st.executeQuery("SELECT OrderID, CustomerID, OrderDate, Name FROM `Orders` INNER JOIN Customer"
                + " USING(CustomerID) WHERE OrderID = " + id + ";");

        while (rs.next()) {
            return new OrderResponse(
                    rs.getInt("OrderID"),
                    rs.getString("Name"),
                    productList,
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
