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
    private TestRepository testTypeRepository;

    public Test save(Test test) {
        return testTypeRepository.save(test);
    }
    public Optional<Test> findById(Integer id) { return  testTypeRepository.findById(id); }
    public List<Test> findAll() {
        return testTypeRepository.findAll(); }

    public void remove(Integer id) {
        testTypeRepository.deleteById(id);
    }
}
