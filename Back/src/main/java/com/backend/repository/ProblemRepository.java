package com.backend.repository;

import com.backend.model.Problem;
import com.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

    List<Problem> findBySubject(Subject subject);

}
