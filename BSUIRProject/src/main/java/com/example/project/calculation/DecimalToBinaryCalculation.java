package com.example.project.calculation;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DecimalToBinaryCalculation {

    public int fromDecimalToBinary(int number) {
        StringBuilder binary = new StringBuilder();

        while (number > 0) {
            int bit = number % 2;
            binary.append(bit);
            number /= 2;
        }
        return Integer.parseInt(binary.reverse().toString());
    }

    public int fromDecimalToBinaryBulk(List<Integer> listOfNumbers) {
        return fromDecimalToBinary(listOfNumbers.get(0));
    }
}
