package com.example.project.controllers;


import com.example.project.async.NumberAsync;
import com.example.project.cache.Cache;
import com.example.project.calculation.DecimalToBinaryCalculation;
import com.example.project.counter.Counter;
import com.example.project.counter.CounterThread;
import com.example.project.exceptions.BadURLArgumentsException;
import com.example.project.models.Numbers;
import com.example.project.services.NumberService;
import com.example.project.validations.DecimalToBinaryValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DecimalToBinaryController {

    private final DecimalToBinaryCalculation decimalToBinaryCalculation;
    private static final Logger logger = LogManager.getLogger(DecimalToBinaryController.class);
    private final DecimalToBinaryValidation decimalToBinaryValidation;
    private Cache<Integer, Integer> cache = new Cache<>();
    private final NumberService numberService;
    private final NumberAsync numberAsync;

    @Autowired
    public DecimalToBinaryController(DecimalToBinaryCalculation decimalToBinaryCalculation, DecimalToBinaryValidation decimalToBinaryValidation,
                                     Cache<Integer, Integer> cache, NumberService numberService, NumberAsync numberAsync){
        this.decimalToBinaryCalculation = decimalToBinaryCalculation;
        this.decimalToBinaryValidation = decimalToBinaryValidation;
        this.cache = cache;
        this.numberService = numberService;
        this.numberAsync = numberAsync;
    }



    @GetMapping("/convertToBinary")
    public ResponseEntity<?> index(@RequestParam("number")int number)
            throws BadURLArgumentsException {

        CounterThread counter = new CounterThread();
        counter.start();

        logger.info("GetMapping to address: localhost:8228/convertToBinary?number=...");
        Numbers numbers = new Numbers();
        decimalToBinaryValidation.validate(number);

        numbers.setDecimalNumber(number);

        String response = new String();
        if(cache.isContains(number)){
            logger.info("Result from cache");
            response = "Result from cache: " + cache.get(number);
            numbers.setBinaryNumber(cache.get(number));
        }
        else{
            logger.info("Calculate");
            int result = decimalToBinaryCalculation.fromDecimalToBinary(number);
            numbers.setBinaryNumber(result);
            logger.info("Push in cache");
            cache.push(number, result);
            response = "Result: " + result;
        }

        numberService.save(numbers);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/counter")
    public ResponseEntity<?>index1(){
        return new ResponseEntity<>("Counter = " + Counter.getCounter(), HttpStatus.OK);
    }


    public static class BulkRequest {
        private List<Integer> listOfNumbers;

        public BulkRequest() {

        }

        public List<Integer> getListOfNumbers() {
            return listOfNumbers;
        }

        public void setListOfNumbers(List<Integer> listOfNumbers) {
            this.listOfNumbers = listOfNumbers;
        }
    }

    @PostMapping("/convertToBinaryBulk")
    public ResponseEntity<?> bulkOperation(@RequestBody BulkRequest requestBody) throws BadURLArgumentsException {
        List<Integer> resultList = requestBody.getListOfNumbers().stream()
                .map(decimalToBinaryCalculation::fromDecimalToBinary)
                .collect(Collectors.toList());

        int max = requestBody.getListOfNumbers().stream().max(Integer::compareTo).orElse(0);
        int min = requestBody.getListOfNumbers().stream().min(Integer::compareTo).orElse(0);
        double average = requestBody.getListOfNumbers().stream().mapToInt(Integer::intValue).average().orElse(0);


        return new ResponseEntity<>("Result: " + resultList
                + "\nMax: " + max
                + "\nMin: " + min
                + "\nAverage: " + average, HttpStatus.OK);
    }

    @PostMapping("/async")
    public Integer async(@RequestBody Numbers numbers){
        int id = numberAsync.createAsync(numbers);

        numberAsync.calculateAsync(id);

        return id;
    }

    @GetMapping("/result/{id}")
    public Numbers result(@PathVariable("id")int id){
        return numberService.findOne(id);
    }
}
