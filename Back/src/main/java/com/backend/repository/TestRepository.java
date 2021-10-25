package com.backend.repository;

import com.backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    List<Test> findAll();
    Test save(Test test);
    Optional<Test> findById(Integer id);
    void deleteById(Integer id);
}
