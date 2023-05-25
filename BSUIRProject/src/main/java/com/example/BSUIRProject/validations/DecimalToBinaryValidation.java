package com.example.BSUIRProject.validations;

import com.example.BSUIRProject.exceptions.BadURLArgumentsException;
import org.springframework.stereotype.Component;

@Component
public class DecimalToBinaryValidation {

    public void validate(int number) throws BadURLArgumentsException {
        if (number < 0) {
            throw new BadURLArgumentsException("Illegal arguments");
        }
    }

}
