package com.backend.controller;

import com.backend.dto.ProblemDTO;
import com.backend.dto.SubjectDTO;
import com.backend.model.KnowledgeState;
import com.backend.model.Problem;
import com.backend.model.Question;
import com.backend.model.Subject;
import com.backend.service.KnowledgeStateService;
import com.backend.service.ProblemService;
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
@RequestMapping(value = "/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private KnowledgeStateService knowledgeStateService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveProblem(@RequestBody ProblemDTO problemDTO){

        Subject subject = subjectService.findById(problemDTO.getSubjectId()).orElse(null);

        if(subject != null) {

            if (problemDTO.getName().equals("") || problemDTO.getDescription().equals("")) {
                return new ResponseEntity<Integer>(0, HttpStatus.NOT_MODIFIED);
            }

            Problem problem = new Problem(problemDTO.getName(), problemDTO.getDescription(), subject);

            Optional<KnowledgeState> knowledgeState = knowledgeStateService.findById(problemDTO.getKnowledgeStateId());
            if(knowledgeState.isPresent() ) {
                knowledgeState.ifPresent(knowledgeState1 -> {
                    problem.setKnowledgeStateId(knowledgeState1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            problemService.save(problem);

            return new ResponseEntity<>(subject.getId(), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<Integer>(0, HttpStatus.NOT_MODIFIED);
        }

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
                    response.add(new ProblemDTO(p.getName(), p.getDescription(), p.getSubject().getId(), p.getKnowledgeStateId().getId()));
                }
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_MODIFIED);
        }
    }


    @DeleteMapping(value = "/{problemId}")
    public ResponseEntity<Void> deleteProblem( @PathVariable("problemId") Integer problemId) {
        Optional<Problem> problem = problemService.findById(problemId);
        if(problem.isPresent() ) {
            problemService.remove(problemId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

    }

}
