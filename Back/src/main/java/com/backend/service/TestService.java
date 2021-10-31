package com.backend.service;

import com.backend.model.Test;
import com.backend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public Test save(Test test) {
        return testRepository.save(test);
    }
    public Optional<Test> findById(Integer id) { return  testRepository.findById(id); }
    public List<Test> findAll() {
        return testRepository.findAll(); }

    public void remove(Integer id) {
        testRepository.deleteById(id);
    }
}
