package com.backend.repository;

import com.backend.model.Section;
import com.backend.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {

    List<Section> findByTestId(Test test);

}
