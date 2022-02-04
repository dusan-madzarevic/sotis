package com.backend.repository;

import com.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KnowledgeStateRepository extends JpaRepository<KnowledgeState, Integer> {
    List<KnowledgeState> findAll();
    KnowledgeState save(KnowledgeState knowledgeState);
    Optional<KnowledgeState> findById(Integer id);
    void deleteById(Integer id);
    List<KnowledgeState> findByStudentId(Student student);

    @Query(value = "SELECT * FROM knowledge_state WHERE id IN(SELECT knowledge_state_id FROM knowledge_state_problems WHERE problem_id = ?1)", nativeQuery = true)
    List<KnowledgeState> findStatesWithProblem(int id);

    @Query(value = "DELETE FROM knowledge_state WHERE student_id = ?1", nativeQuery = true)
    @Modifying
    void resetStates(int studentId);
}
