package org.kainos.ea.db;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private DatabaseConnector databaseConnector = new DatabaseConnector();
    public List<Product> getAllProducts() throws SQLException {
        Connection c = databaseConnector.getConnection();
            Statement st = c.createStatement();

            ResultSet rs = st.executeQuery("SELECT Product_ID, Product_Name, Description, Price FROM `Product`");

            List<Product> productList = new ArrayList<>();

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("Product_ID"),
                        rs.getString("Product_Name"),
                        rs.getString("Description"),
                        rs.getDouble("Price")
                );

                productList.add(product);
            }
            return productList;
    }

    public Product getProductByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();

        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT Product_ID, Product_Name, Description, Price FROM `Product`" +
                " WHERE Product_ID = " + id);

        while (rs.next()) {
            return new Product(
                    rs.getInt("Product_ID"),
                    rs.getString("Product_Name"),
                    rs.getString("Description"),
                    rs.getDouble("Price")
            );
        }

        return null;
    }

    public int createProduct(ProductRequest product) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String insertStatement = "INSERT INTO Product(Product_Name, Description, Price) VALUES(?,?,?)";

        PreparedStatement st = c.prepareStatement(insertStatement, Statement.RETURN_GENERATED_KEYS);

        st.setString(1, product.getProductName());
        st.setString(2, product.getDescription());
        st.setDouble(3, product.getPrice());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if(rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public void updateProduct(int id, ProductRequest product) throws SQLException {
        Connection c = databaseConnector.getConnection();

        String updateStatement = "UPDATE PRODUCT SET Product_Name = ?, Description = ?, Price = ? WHERE ProductID = ?";

        PreparedStatement st = c.prepareStatement(updateStatement);

        st.setString(1, product.getProductName());
        st.setString(2, product.getDescription());
        st.setDouble(3, product.getPrice());
        st.setInt(4, id);

        st.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        try (Connection c = databaseConnector.getConnection()){
            String deleteStatement = "DELETE FROM PRODUCT WHERE Product_ID = ?";

            PreparedStatement st = c.prepareStatement(deleteStatement);

            st.setInt(1, id);

            st.executeUpdate();
        }
    }
}
