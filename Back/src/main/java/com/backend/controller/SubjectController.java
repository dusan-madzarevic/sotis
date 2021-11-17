package com.backend.controller;

import com.backend.dto.SubjectDTO;
import com.backend.model.Subject;
import com.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveSubject(@RequestBody SubjectDTO subjectDTO){

        if(subjectDTO.getName().equals("") || subjectDTO.getCode().equals(""))
        {
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_MODIFIED);
        }

        Subject subject = new Subject(subjectDTO.getName(),subjectDTO.getCode());

        subjectService.save(subject);

        return new ResponseEntity<>(subject.getId(), HttpStatus.CREATED);


    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<SubjectDTO>> getSubjects() {
        List<Subject> subjects = subjectService.findAll();
        List<SubjectDTO> response = new ArrayList<>();
        if(subjects != null)
        {
            for (Subject s:
                 subjects) {
                response.add(new SubjectDTO(s.getName(), s.getCode()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
    }


    @DeleteMapping(value = "/{subjectId}")
    public ResponseEntity<Void> deleteSubject( @PathVariable("subjectId") Integer subjectId) {
        Optional<Subject> subject = subjectService.findById(subjectId);
        if(subject.isPresent() ) {
            subjectService.remove(subjectId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }

}
