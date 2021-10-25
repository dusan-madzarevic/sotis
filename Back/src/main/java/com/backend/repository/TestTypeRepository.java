package com.backend.repository;

import com.backend.model.TestType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestTypeRepository extends JpaRepository<TestType, Integer> {
    List<TestType> findAll();
    TestType save(TestType testType);
    Optional<TestType> findById(Integer id);
    void deleteById(Integer id);
}
