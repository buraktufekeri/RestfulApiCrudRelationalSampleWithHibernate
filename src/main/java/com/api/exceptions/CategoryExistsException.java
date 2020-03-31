package com.api.exceptions;

public class CategoryExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public CategoryExistsException(String message) {
        super(message);
    }
}
