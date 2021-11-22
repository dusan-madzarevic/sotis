package com.backend.controller;

import com.backend.dto.SubjectDTO;
import com.backend.dto.SubjectProfessorDTO;
import com.backend.model.Professor;
import com.backend.model.Subject;
import com.backend.service.ProfessorService;
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
    @Autowired
    private ProfessorService professorService;

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

    @PutMapping(value = "/professors", consumes = "application/json")
    public ResponseEntity<Integer> saveSubjectProfessors(@RequestBody SubjectProfessorDTO subjectProfessorDTO){

        if(subjectProfessorDTO.getSubjectId() == null || subjectProfessorDTO.getSubjectId() == 0 || subjectProfessorDTO.getProfessors().isEmpty())
        {
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_MODIFIED);
        }

        Subject subject = subjectService.findById(subjectProfessorDTO.getSubjectId()).orElse(null);
        List<Professor> professors = professorService.findAllByUserType("Professor");
        for(Professor p : professors){
            for(Integer professorIdDto : subjectProfessorDTO.getProfessors()){
                if(p.getId() == professorIdDto){
                    subject.getProfessors().add(p);
                }
            }
        }
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
                response.add(new SubjectDTO(s.getId(),s.getName(), s.getCode()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/byProfessor/{id}", produces = "application/json")
    public ResponseEntity<List<Subject>> getProfessorBySubject(@PathVariable("id") Integer professorId) {
        Professor professor = professorService.findById(professorId).orElse(null);
        if(professor != null)
        {
            List<Subject> subjects = subjectService.findByProfessors(professor);

            return new ResponseEntity<>(subjects, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
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
