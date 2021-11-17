package com.backend.controller;

import com.backend.dto.StudentSubjectDTO;
import com.backend.model.*;
import com.backend.service.KnowledgeStateService;
import com.backend.service.StudentService;
import com.backend.service.StudentSubjectService;
import com.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/studentSubject")
public class StudentSubjectController {
    @Autowired
    private StudentSubjectService studentSubjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private KnowledgeStateService knowledgeStateService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveStudentSubject(@RequestBody StudentSubjectDTO studentSubjectDTO, HttpServletRequest httpServletRequest) {
        try {
            StudentSubject studentSubject = new StudentSubject();
            if(studentSubjectDTO.getStudentId() == null || studentSubjectDTO.getStudentId() == 0 ||  studentSubjectDTO.getSubjectId() == null || studentSubjectDTO.getSubjectId() == 0 || studentSubjectDTO.getKnowledgeStateId() == null || studentSubjectDTO.getKnowledgeStateId() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<Student> student = studentService.findById(studentSubjectDTO.getStudentId());
            if(student.isPresent() ) {
                student.ifPresent(student1 -> {
                    studentSubject.setStudentId(student1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<Subject> subject = subjectService.findById(studentSubjectDTO.getSubjectId());
            if(subject.isPresent() ) {
                subject.ifPresent(subject1 -> {
                    studentSubject.setSubjectId(subject1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<KnowledgeState> knowledgeState = knowledgeStateService.findById(studentSubjectDTO.getKnowledgeStateId());
            if(knowledgeState.isPresent() ) {
                knowledgeState.ifPresent(knowledgeState1 -> {
                    studentSubject.setKnowledgeStateId(knowledgeState1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            studentSubjectService.save(studentSubject);
            return new ResponseEntity<>(studentSubject.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<StudentSubject>> getStudentSubjects() {
        List<StudentSubject> studentSubjects = studentSubjectService.findAll();
        if(studentSubjects != null)
        {
            return new ResponseEntity<>(studentSubjects, HttpStatus.OK);
        }
        return new ResponseEntity<>(studentSubjects, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{studentSubjectId}/{studentId}/{knowledgeStateId}/{subjectId}")
    public ResponseEntity<Void> deleteStudentSubject( @PathVariable("studentSubjectId") Integer studentSubjectId, @PathVariable("studentId") Integer studentId, @PathVariable("knowledgeStateId") Integer knowledgeStateId, @PathVariable("subjectId") Integer subjectId) {
        Optional<StudentSubject> studentSubject = studentSubjectService.findById(studentSubjectId);
        Optional<Student> student = studentService.findById(studentId);
        Optional<KnowledgeState> knowledgeState = knowledgeStateService.findById(knowledgeStateId);
        Optional<Subject> subject = subjectService.findById(subjectId);
        if(student.isPresent() ) {
            student.ifPresent(student1 -> {
                student1.getStudentSubjects().remove(studentSubject.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        if(knowledgeState.isPresent() ) {
            knowledgeState.ifPresent(knowledgeState1 -> {
                knowledgeState1.getStudentSubjects().remove(studentSubject.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        if(subject.isPresent() ) {
            subject.ifPresent(subject1 -> {
                subject1.getStudentSubjects().remove(studentSubject.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        studentSubjectService.remove(studentSubjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
