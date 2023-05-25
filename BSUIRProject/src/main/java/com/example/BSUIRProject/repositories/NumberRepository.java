package com.example.BSUIRProject.repositories;

import com.example.BSUIRProject.models.Numbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NumberRepository extends JpaRepository<Numbers,Integer> {

    public Numbers findByDecimalNumberContains(int number);

}
