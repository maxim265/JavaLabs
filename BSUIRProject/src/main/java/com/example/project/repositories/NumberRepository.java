package com.example.project.repositories;

import com.example.project.models.Numbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository<Numbers,Integer> {

    public Numbers findByDecimalNumberContains(int number);

}
