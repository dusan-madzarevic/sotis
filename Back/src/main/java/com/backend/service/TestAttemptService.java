package com.backend.service;

import com.backend.model.TestAttempt;
import com.backend.repository.TestAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestAttemptService {
    @Autowired
    private TestAttemptRepository testRepository;

    public TestAttempt save(TestAttempt testAttempt) {
        return testRepository.save(testAttempt);
    }
    public Optional<TestAttempt> findById(Integer id) { return  testRepository.findById(id); }
    public List<TestAttempt> findAll() {
        return testRepository.findAll(); }

    public void remove(Integer id) {
        testRepository.deleteById(id);
    }
}
