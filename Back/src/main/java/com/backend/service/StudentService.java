package com.backend.service;

import com.backend.model.Student;
import com.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void remove(Integer id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findAllByUserType(String userType) {
        return studentRepository.findAllByUserType(userType); }

    public Optional<Student> findById(Integer id) { return  studentRepository.findById(id); }

    public Optional<Student> findByUsername(String username){
        return studentRepository.findByUsername(username);
    }
}
