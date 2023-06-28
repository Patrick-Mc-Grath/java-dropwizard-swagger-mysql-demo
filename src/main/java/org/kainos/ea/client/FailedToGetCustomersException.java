package org.kainos.ea.client;

public class FailedToGetCustomersException extends Throwable {
    @Override
    public String getMessage(){
        return "Failed to get customers from the database.";
    }
}
