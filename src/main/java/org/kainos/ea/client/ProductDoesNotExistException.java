package org.kainos.ea.client;

public class ProductDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "No products exist with this id.";
    }
}
