package com.backend.service;

import com.backend.model.*;

import com.backend.repository.KnowledgeStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeStateService {
    @Autowired
    private KnowledgeStateRepository knowledgeStateRepository;

    public KnowledgeState save(KnowledgeState knowledgeState) {
        return knowledgeStateRepository.save(knowledgeState);
    }
    public Optional<KnowledgeState> findById(Integer id) { return  knowledgeStateRepository.findById(id); }
    public List<KnowledgeState> findAll() {
        return knowledgeStateRepository.findAll(); }

    public void remove(Integer id) {
        knowledgeStateRepository.deleteById(id);
    }

    public List<KnowledgeState> findByStudentId(Student student){
        return knowledgeStateRepository.findByStudentId(student);
    }

    public List<KnowledgeState> findBySurmiseId(Surmise surmise){
        return knowledgeStateRepository.findBySurmiseId(surmise);
    }
}
