package com.backend.repository;

import com.backend.model.Answer;
import com.backend.model.Section;
import com.backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    List<Answer> findAll();
    Answer save(Answer answer);
    Optional<Answer> findById(Integer id);
    void deleteById(Integer id);
    List<Answer> findByQuestionId(Integer questionId);
}
