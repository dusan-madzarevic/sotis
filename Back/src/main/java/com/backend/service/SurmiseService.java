package com.backend.service;

import com.backend.model.KnowledgeSpace;
import com.backend.model.Problem;
import com.backend.model.Surmise;
import com.backend.repository.SurmiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurmiseService {
    @Autowired
    private SurmiseRepository surmiseRepository;

    public Surmise save(Surmise surmise) {
        return surmiseRepository.save(surmise);
    }
    public Optional<Surmise> findById(Integer id) { return  surmiseRepository.findById(id); }
    public List<Surmise> findAll() {
        return surmiseRepository.findAll(); }

    public void remove(Integer id) {
        surmiseRepository.deleteById(id);
    }

    public List<Surmise> findByProblemId(Problem problem){
        return surmiseRepository.findByProblemId(problem);
    }

    public List<Surmise> findByKnowledgeSpaceId(KnowledgeSpace knowledgeSpace){
        return surmiseRepository.findByKnowledgeSpaceId(knowledgeSpace);
    }
}
