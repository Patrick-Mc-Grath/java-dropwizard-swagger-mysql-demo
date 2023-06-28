package org.kainos.ea.client;

public class FailedToCreateProductException extends Throwable {
    @Override
    public String getMessage() {
        return "Could not add product to database";
    }
}
