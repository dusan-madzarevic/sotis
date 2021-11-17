package com.backend.service;

import com.backend.model.StudentSubject;
import com.backend.repository.StudentSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSubjectService {
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;

    public StudentSubject save(StudentSubject studentSubject) {
        return studentSubjectRepository.save(studentSubject);
    }
    public Optional<StudentSubject> findById(Integer id) { return  studentSubjectRepository.findById(id); }
    public List<StudentSubject> findAll() {
        return studentSubjectRepository.findAll(); }

    public void remove(Integer id) {
        studentSubjectRepository.deleteById(id);
    }

    /*public List<StudentSubject> findByQuestion(Question question){
        return studentSubjectRepository.findByQuestionId(question);
    }*/
}
