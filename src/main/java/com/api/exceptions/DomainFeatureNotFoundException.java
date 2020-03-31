package com.api.exceptions;

public class DomainFeatureNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public DomainFeatureNotFoundException(String message) {
        super(message);
    }
}
