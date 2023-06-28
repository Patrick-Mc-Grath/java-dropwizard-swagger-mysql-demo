package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "No orders exist with this ID.";
    }
}
