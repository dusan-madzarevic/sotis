package com.backend.service;

import com.backend.model.*;
import com.backend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question save(Question question) {
        return questionRepository.save(question);
    }
    public Optional<Question> findById(Integer id) { return  questionRepository.findById(id); }
    public List<Question> findAll() {
        return questionRepository.findAll(); }

    public void remove(Integer id) {
        questionRepository.deleteById(id);
    }

    public List<Question> findBySection(Section section){
        return questionRepository.findBySectionId(section);
    }

    public List<Question> findByProblemId(Problem problem){
        return questionRepository.findByProblems(problem);
    }
}
