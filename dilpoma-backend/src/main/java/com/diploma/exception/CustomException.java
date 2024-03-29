package com.diploma.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public CustomException(HttpStatus httpStatus, String message){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
