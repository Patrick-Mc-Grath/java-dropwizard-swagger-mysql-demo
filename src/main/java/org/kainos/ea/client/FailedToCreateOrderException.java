package org.kainos.ea.client;

public class FailedToCreateOrderException extends Throwable {
    @Override
    public String getMessage(){
        return "Could not add order to database";
    }
}
