package com.example.project.services;

import com.example.project.models.Numbers;
import com.example.project.repositories.NumberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NumberService {
    private final NumberRepository numberRepository;

    public NumberService(NumberRepository numberRepository) {
        this.numberRepository = numberRepository;
    }

    public Numbers save(Numbers numbers) {
//        Optional<Numbers> dataOptional = numberRepository.findOne(Example.of(numbers));
//        if (!dataOptional.isPresent()) numberRepository.save(numbers);

        return numberRepository.saveAndFlush(numbers);
    }

    public Numbers findOne(int id) {
        Optional<Numbers> numbers = numberRepository.findById(id);

        return numbers.orElse(null);
    }
}
