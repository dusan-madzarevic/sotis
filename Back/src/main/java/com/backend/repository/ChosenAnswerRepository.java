package com.backend.repository;

import com.backend.model.ChosenAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChosenAnswerRepository extends JpaRepository<ChosenAnswer, Integer> {
    List<ChosenAnswer> findAll();
    ChosenAnswer save(ChosenAnswer chosenAnswer);
    Optional<ChosenAnswer> findById(Integer id);
    void deleteById(Integer id);
}
