package com.vista.accouting.exceptions;

public class UserFoundException extends RuntimeException{

    public UserFoundException(){
        super(String.format("User is replicate"));
    }
}
