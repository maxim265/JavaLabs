package com.example.BSUIRProject.services;

import com.example.BSUIRProject.models.Numbers;
import com.example.BSUIRProject.repositories.NumberRepository;
import org.springframework.data.domain.Example;
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
