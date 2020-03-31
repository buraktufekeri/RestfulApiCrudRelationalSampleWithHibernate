package com.api.exceptions;

public class DomainNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DomainNotFoundException(String message) {
        super(message);
    }
}
