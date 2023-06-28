package org.kainos.ea.client;

public class CustomerDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "No customers exist with that ID.";
    }
}
