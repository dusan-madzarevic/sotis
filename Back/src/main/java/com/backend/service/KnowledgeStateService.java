package com.backend.service;

import com.backend.model.KnowledgeState;
import com.backend.repository.KnowladgeStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeStateService {
    @Autowired
    private KnowladgeStateRepository knowladgeStateRepository;

    public KnowledgeState save(KnowledgeState knowledgeState) {
        return knowladgeStateRepository.save(knowledgeState);
    }
    public Optional<KnowledgeState> findById(Integer id) { return  knowladgeStateRepository.findById(id); }
    public List<KnowledgeState> findAll() {
        return knowladgeStateRepository.findAll(); }

    public void remove(Integer id) {
        knowladgeStateRepository.deleteById(id);
    }

    /*public List<KnowledgeState> findByQuestion(Question question){
        return knowladgeStateRepository.findByQuestionId(question);
    }*/
}
