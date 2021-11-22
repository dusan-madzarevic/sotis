package com.backend.service;

import com.backend.model.Professor;
import com.backend.model.Subject;
import com.backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void remove(Integer id) {
        subjectRepository.deleteById(id);
    }

    public Optional<Subject> findById(Integer id) { return  subjectRepository.findById(id); }

    public List<Subject> findAll(){return subjectRepository.findAll();}

    public List<Subject> findByProfessors(Professor professor){
        return subjectRepository.findByProfessors(professor);
    }
}
