package com.vista.accouting.exceptions;

public class NotFoundUserException  extends RuntimeException {

    public NotFoundUserException() {

        super(String.format("User not found"));
    }

    public NotFoundUserException(Long id) {

        super(String.format("User with Id %d not found", id));
    }
}