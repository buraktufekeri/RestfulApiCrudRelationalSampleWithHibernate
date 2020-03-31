package com.api.exceptions;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({HttpClientErrorException.BadRequest.class})
    public static ResponseEntity<Object> handleBadRequestException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>("Bad Request", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public static ResponseEntity<Object> handleResourceNotFoundException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({HttpServerErrorException.InternalServerError.class})
    public static ResponseEntity<Object> handleInternalServerErrorException(Exception exception, WebRequest webRequest){
        return new ResponseEntity<>("Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}