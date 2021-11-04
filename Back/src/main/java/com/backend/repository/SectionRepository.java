package com.backend.repository;

import com.backend.model.Section;
import com.backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    List<Section> findByTestId(Test test);
    List<Section> findAll();
    Section save(Section section);
    Optional<Section> findById(Integer id);
    void deleteById(Integer id);
}
