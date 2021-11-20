package com.backend.service;

import com.backend.model.Answer;
import com.backend.model.KnowledgeSpace;
import com.backend.model.Question;
import com.backend.model.Subject;
import com.backend.repository.AnswerRepository;
import com.backend.repository.KnowledgeSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KnowledgeSpaceService {
    @Autowired
    private KnowledgeSpaceRepository knowledgeSpaceRepository;

    public KnowledgeSpace save(KnowledgeSpace knowledgeSpace) {
        return knowledgeSpaceRepository.save(knowledgeSpace);
    }
    public Optional<KnowledgeSpace> findById(Integer id) { return  knowledgeSpaceRepository.findById(id); }
    public List<KnowledgeSpace> findAll() {
        return knowledgeSpaceRepository.findAll(); }

    public void remove(Integer id) {
        knowledgeSpaceRepository.deleteById(id);
    }

    public List<KnowledgeSpace> findBySubject(Subject subject){
        return knowledgeSpaceRepository.findBySubjectId(subject);
    }

}
