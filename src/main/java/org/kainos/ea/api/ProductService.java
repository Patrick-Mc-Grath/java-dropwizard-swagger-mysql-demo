package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.db.ProductDao;
import org.kainos.ea.core.ProductValidator;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    ProductDao productDao = new ProductDao();
    ProductValidator productValidator = new ProductValidator();
    public List<Product> getAllProducts() throws FailedToGetProductsException {
        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }

        return productList;
    }

    public Product getProductByID(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
           Product product = productDao.getProductByID(id);

            if(product == null){
                throw new ProductDoesNotExistException();
            }

            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }
    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try {
            String validation = productValidator.isValidProduct(product);
            if(validation != null) {
                throw new InvalidProductException(validation);
            }
            int id = productDao.createProduct(product);

            if(id == -1){
                throw new FailedToCreateProductException();
            }

            return id;
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }

    public void updateProduct(ProductRequest product, int id) throws FailedToUpdateProductException, ProductDoesNotExistException, InvalidProductException {
        try{
            String validation = productValidator.isValidProduct(product);
            if(validation != null){
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductByID(id);

            if(productToUpdate == null){
                throw new ProductDoesNotExistException();
            }

            productDao.updateProduct(id, product);
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();
        }
    }

    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedtoDeleteProductException {
        try{
            Product productToDelete = productDao.getProductByID(id);

            if(productToDelete == null){
                throw new ProductDoesNotExistException();
            }

            productDao.deleteProduct(id);
        } catch(SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedtoDeleteProductException();
        }
    }
}