package com.backend.service;


import com.backend.model.Section;
import com.backend.model.Test;
import com.backend.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    public Section save(Section section) {
        return sectionRepository.save(section);
    }
    public Optional<Section> findById(Integer id) { return  sectionRepository.findById(id); }
    public List<Section> findAll() {
        return sectionRepository.findAll(); }

    public List<Section> findByTest(Test test){
        return sectionRepository.findByTestId(test);
    }

    public void remove(Integer id) {
        sectionRepository.deleteById(id);
    }


}
