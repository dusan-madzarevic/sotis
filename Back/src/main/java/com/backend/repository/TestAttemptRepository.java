package com.backend.repository;

import com.backend.model.Test;
import com.backend.model.TestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestAttemptRepository extends JpaRepository<TestAttempt, Integer> {
    List<TestAttempt> findAll();
    TestAttempt save(TestAttempt testAttempt);
    Optional<TestAttempt> findById(Integer id);
    void deleteById(Integer id);

    List<TestAttempt> findByTestId(Test test);
}
