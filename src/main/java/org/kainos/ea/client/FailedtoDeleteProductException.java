package org.kainos.ea.client;

public class FailedtoDeleteProductException extends Throwable {
    @Override
    public String getMessage(){
        return "Unable to delete the order from the database.";
    }
}
