package com.api.exceptions;

public class DomainExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public DomainExistsException(String message) {
        super(message);
    }
}
