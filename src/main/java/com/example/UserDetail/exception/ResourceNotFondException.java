package com.example.UserDetail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class ResourceNotFondException  extends RuntimeException {
    public ResourceNotFondException(String massage){
        super(massage);
    }
}
