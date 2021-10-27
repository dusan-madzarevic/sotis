package com.backend.repository;

import com.backend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findAll();
    Question save(Question question);
    Optional<Question> findById(Integer id);
    void deleteById(Integer id);
}
