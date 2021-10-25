package com.backend.service;

import com.backend.model.TestType;
import com.backend.repository.TestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestTypeService {
    @Autowired
    private TestTypeRepository testTypeRepository;

    public TestType save(TestType testType) {
        return testTypeRepository.save(testType);
    }
    public Optional<TestType> findById(Integer id) { return  testTypeRepository.findById(id); }
    public List<TestType> findAll() {
        return testTypeRepository.findAll(); }

    public void remove(Integer id) {
        testTypeRepository.deleteById(id);
    }
}
