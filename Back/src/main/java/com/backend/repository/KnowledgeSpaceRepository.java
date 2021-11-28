package com.backend.repository;

import com.backend.model.KnowledgeSpace;
import com.backend.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowledgeSpaceRepository extends JpaRepository<KnowledgeSpace, Integer> {
    List<KnowledgeSpace> findAll();
    KnowledgeSpace save(KnowledgeSpace knowledgeSpace);
    Optional<KnowledgeSpace> findById(Integer id);
    void deleteById(Integer id);
    List<KnowledgeSpace> findBySubjectId(Subject subject);
}
