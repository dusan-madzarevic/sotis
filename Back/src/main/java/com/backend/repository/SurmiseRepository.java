package com.backend.repository;

import com.backend.model.KnowledgeSpace;
import com.backend.model.Problem;
import com.backend.model.Surmise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurmiseRepository extends JpaRepository<Surmise, Integer> {
    List<Surmise> findAll();
    Surmise save(Surmise surmise);
    Optional<Surmise> findById(Integer id);
    void deleteById(Integer id);
    List<Surmise> findByProblemId(Problem problem);
    List<Surmise> findByKnowledgeSpaceId(KnowledgeSpace knowledgeSpace);
}
