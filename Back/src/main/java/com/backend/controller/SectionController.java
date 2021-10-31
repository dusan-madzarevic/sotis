package com.backend.controller;

import com.backend.dto.SectionDTO;
import com.backend.model.Section;
import com.backend.model.Test;
import com.backend.service.SectionService;
import com.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/section")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private TestService testService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveQuestion(@RequestBody SectionDTO sectionDTO, HttpServletRequest httpServletRequest) {
        try {
            Section section = new Section();
            if(sectionDTO.getTestId() == null || sectionDTO.getTestId() == 0 || sectionDTO.getName().equals(""))
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            section.setName(sectionDTO.getName());

            Optional<Test> test = testService.findById(sectionDTO.getTestId());

            if(test.isPresent() ) {
                test.ifPresent(test1 -> {
                    section.setTestId(test1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }


            sectionService.save(section);
            return new ResponseEntity<>(section.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(value = "/byTest/{id}", produces = "application/json")
    public ResponseEntity<List<SectionDTO>> getTestSections(@PathVariable("id") Integer testId) {
        Optional<Test> test = testService.findById(testId);
        List<SectionDTO> response = new ArrayList<>();

        if(test.isPresent())
        {
            List<Section> sections = sectionService.findByTest(test.get());

            for (Section section : sections)
            {
                response.add(new SectionDTO(testId, section.getName()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteSection(@PathVariable Integer id) {
        sectionService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
