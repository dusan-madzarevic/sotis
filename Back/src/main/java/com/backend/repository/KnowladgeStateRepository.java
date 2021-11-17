package com.backend.repository;

import com.backend.model.KnowledgeState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowladgeStateRepository extends JpaRepository<KnowledgeState, Integer> {
    List<KnowledgeState> findAll();
    KnowledgeState save(KnowledgeState knowledgeState);
    Optional<KnowledgeState> findById(Integer id);
    void deleteById(Integer id);
    //List<KnowledgeState> findByQuestionId(Question question);
}
