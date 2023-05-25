package com.example.BSUIRProject.controllers;

import com.example.BSUIRProject.calculation.DecimalToBinaryCalculation;
import com.example.BSUIRProject.exceptions.BadURLArgumentsException;
import com.example.BSUIRProject.validations.DecimalToBinaryValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertToBinaryTest {

    @Test
    public void validationShouldThrowException() {
        DecimalToBinaryValidation decimalToBinaryValidation = new DecimalToBinaryValidation();
        Assertions.assertThrows(BadURLArgumentsException.class, () -> {
            decimalToBinaryValidation.validate(-7);
        });
    }

    @Test
    public void resultOfConvert() {
        DecimalToBinaryCalculation decimalToBinaryCalculation = new DecimalToBinaryCalculation();
        Assertions.assertEquals(1010, decimalToBinaryCalculation.fromDecimalToBinary(10));
    }

}
