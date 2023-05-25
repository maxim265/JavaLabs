package com.example.BSUIRProject.async;

import com.example.BSUIRProject.calculation.DecimalToBinaryCalculation;
import com.example.BSUIRProject.models.Numbers;
import com.example.BSUIRProject.services.NumberService;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class NumberAsync {

    private final NumberService numberService;
    private final DecimalToBinaryCalculation calculation;

    public NumberAsync(NumberService numberService, DecimalToBinaryCalculation calculation) {
        this.numberService = numberService;
        this.calculation = calculation;
    }

    public Integer createAsync(Numbers numbers) {
        Numbers newModelNumber = new Numbers();

        newModelNumber.setDecimalNumber(numbers.getDecimalNumber());

        newModelNumber = numberService.save(newModelNumber);

        return newModelNumber.getId();
    }

    public void calculateAsync(int id) {
        CompletableFuture.supplyAsync(() -> {
            try {
                Numbers result = numberService.findOne(id);

                Thread.sleep(20000);
                result.setBinaryNumber(calculation.fromDecimalToBinary(result.getDecimalNumber()));

                numberService.save(result);

                return result.getId();

            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
    }

}