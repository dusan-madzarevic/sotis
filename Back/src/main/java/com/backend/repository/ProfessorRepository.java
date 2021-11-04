package com.backend.repository;

import com.backend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Professor save(Professor professor);
    void deleteById(Integer id);
    List<Professor> findAllByUserType(String userType);
    Optional<Professor> findById(Integer id);

    Optional<Professor> findByUsername(String username);
}
