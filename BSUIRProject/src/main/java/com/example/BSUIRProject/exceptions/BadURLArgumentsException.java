package com.example.BSUIRProject.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "bad url arguments")
public class BadURLArgumentsException extends Exception {

    private static final Logger logger = LogManager.getLogger(BadURLArgumentsException.class);

    public BadURLArgumentsException(String message){
        super(message);
        logger.error(message);
    }
}

