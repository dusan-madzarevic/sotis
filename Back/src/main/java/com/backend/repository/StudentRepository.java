package com.backend.repository;

import com.backend.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student save(Student student);
    void deleteById(Integer id);
    List<Student> findAllByUserType(String userType);
    Optional<Student> findById(Integer id);

    Optional<Student> findByUsername(String username);
}
