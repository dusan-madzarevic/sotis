package com.backend.repository;

import com.backend.model.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Integer> {
    List<StudentSubject> findAll();
    StudentSubject save(StudentSubject studentSubject);
    Optional<StudentSubject> findById(Integer id);
    void deleteById(Integer id);
    //List<StudentSubject> findByQuestionId(Question question);
}
