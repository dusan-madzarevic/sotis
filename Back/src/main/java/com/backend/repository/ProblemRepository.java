package com.backend.repository;

import com.backend.model.Problem;
import com.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    List<Problem> findBySubject(Subject subject);
    List<Problem> findAll();
    Problem save(Problem problem);
    Optional<Problem> findById(Integer id);
    void deleteById(Integer id);

}
