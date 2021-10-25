package com.backend.service;

import com.backend.model.ChosenAnswer;
import com.backend.repository.ChosenAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChosenAnswerService {
    @Autowired
    private ChosenAnswerRepository chosenAnswerRepository;

    public ChosenAnswer save(ChosenAnswer chosenAnswer) {
        return chosenAnswerRepository.save(chosenAnswer);
    }
    public Optional<ChosenAnswer> findById(Integer id) { return  chosenAnswerRepository.findById(id); }
    public List<ChosenAnswer> findAll() {
        return chosenAnswerRepository.findAll(); }

    public void remove(Integer id) {
        chosenAnswerRepository.deleteById(id);
    }
}
