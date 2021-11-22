package com.backend.repository;

import com.backend.model.Professor;
import com.backend.model.Subject;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends  JpaRepository<Subject, Integer>{
    List<Subject> findAll();
    Subject save(Subject subject);
    Optional<Subject> findById(Integer id);
    void deleteById(Integer id);
    List<Subject> findByProfessors(Professor professor);
}
