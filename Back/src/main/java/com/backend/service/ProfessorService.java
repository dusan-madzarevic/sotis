package com.backend.service;

import com.backend.model.Professor;
import com.backend.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public void remove(Integer id) {
        professorRepository.deleteById(id);
    }

    public List<Professor> findAllByUserType(String userType) {
        return professorRepository.findAllByUserType(userType); }

    public Optional<Professor> findById(Integer id) { return  professorRepository.findById(id); }

    public Optional<Professor> findByUsername(String username) { return  professorRepository.findByUsername(username); }
}
