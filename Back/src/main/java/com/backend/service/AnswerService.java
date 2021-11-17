package com.backend.service;

import com.backend.model.Answer;
import com.backend.model.Question;
import com.backend.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Answer save(Answer answer) {
        return answerRepository.save(answer);
    }
    public Optional<Answer> findById(Integer id) { return  answerRepository.findById(id); }
    public List<Answer> findAll() {
        return answerRepository.findAll(); }

    public void remove(Integer id) {
        answerRepository.deleteById(id);
    }

    public List<Answer> findByQuestion(Question question){
        return answerRepository.findByQuestionId(question);
    }
}
