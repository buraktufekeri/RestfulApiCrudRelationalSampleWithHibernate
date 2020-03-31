package com.api.exceptions;

public class CategoryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoryNotFoundException(String message) {
        super(message);
    }
}