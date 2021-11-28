package com.backend.controller;

import com.backend.dto.LearnedProblemDTO;
import com.backend.dto.ProblemDTO;
import com.backend.dto.ProblemQuestionsDTO;
import com.backend.model.*;
import com.backend.service.ProblemService;
import com.backend.service.QuestionService;
import com.backend.service.StudentService;
import com.backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private QuestionService questionService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveProblem(@RequestBody ProblemDTO problemDTO){

        Subject subject = subjectService.findById(problemDTO.getSubjectId()).orElse(null);

        if(subject != null) {

            if (problemDTO.getName().equals("") || problemDTO.getDescription().equals("")) {
                return new ResponseEntity<Integer>(0, HttpStatus.NOT_MODIFIED);
            }

            Problem problem = new Problem(problemDTO.getName(), problemDTO.getDescription(), subject);

            problemService.save(problem);

            return new ResponseEntity<>(problem.getId(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_MODIFIED);
        }

    }

    @PostMapping(value = "/learnedProblem", consumes = "application/json")
    public ResponseEntity<Void> saveLearnedProblems(@RequestBody LearnedProblemDTO learnedProblemDTO){
        try{
            List<Student> students = studentService.findAllByUserType("Student");
            Optional<Problem> problem = problemService.findById(learnedProblemDTO.getProblemId());

            Set<Student> novi = new HashSet<>();
            if(problem.isPresent() ) {
                problem.ifPresent(problem1 -> {
                    for(Student s : students){
                        for( Integer dtoStudentId : learnedProblemDTO.getStudentId()){
                            if( s.getId() == dtoStudentId){
                                novi.add(s);
                            }
                        }
                    }
                    problem1.setLearnedProblems(novi);
                });
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @PutMapping(value = "/questions",consumes = "application/json")
    public ResponseEntity<Integer> saveProblemQuestions(@RequestBody ProblemQuestionsDTO problemQuestionsDTO, HttpServletRequest httpServletRequest) {
        try {
            if(problemQuestionsDTO.getProblemId() == 0 || problemQuestionsDTO.getProblemId() == null ||  problemQuestionsDTO.getQuestions().isEmpty())
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Problem problem = problemService.findById(problemQuestionsDTO.getProblemId()).orElse(null);
            for(Integer dtoQuestion : problemQuestionsDTO.getQuestions())
            {
                Question question = questionService.findById(dtoQuestion).orElse(null);
                problem.getQuestions().add(question);
            }
            problemService.save(problem);
            return new ResponseEntity<>(problem.getId(), HttpStatus.CREATED);

        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Problem>> getAllProblems() {
        List<Problem> problems = problemService.findAll();
        if(problems != null)
        {
            return new ResponseEntity<>(problems, HttpStatus.OK);
        }
        return new ResponseEntity<>(problems, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/bySubject/{subjectId}", produces = "application/json")
    public ResponseEntity<List<ProblemDTO>> getProblemsForSubject(@PathVariable("subjectId") Integer subjectId) {

        Subject subject = subjectService.findById(subjectId).orElse(null);

        if(subject != null) {

            List<Problem> problems = problemService.findBySubject(subject);
            List<ProblemDTO> response = new ArrayList<>();
            if (problems != null) {
                for (Problem p :
                        problems) {
                    response.add(new ProblemDTO(p.getId(), p.getName(), p.getDescription(), p.getSubject().getId()));
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }


    @DeleteMapping(value = "/{problemId}/{subjectId}")
    public ResponseEntity<Void> deleteProblem(@PathVariable("problemId") Integer problemId, @PathVariable("subjectId") Integer subjectId) {
        Optional<Subject> subject = subjectService.findById(subjectId);
        Optional<Problem> problem = problemService.findById(problemId);
        if (subject.isPresent()) {
            subject.ifPresent(subject1 -> {
                subject1.getProblems().remove(problem.get());
            });
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        problemService.remove(problemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
