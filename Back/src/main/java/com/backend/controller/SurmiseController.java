package com.backend.controller;

import com.backend.dto.KnowledgeSpaceDTO;
import com.backend.dto.SurmiseDTO;
import com.backend.model.KnowledgeSpace;
import com.backend.model.Problem;
import com.backend.model.Subject;
import com.backend.model.Surmise;
import com.backend.service.KnowledgeSpaceService;
import com.backend.service.ProblemService;
import com.backend.service.SurmiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value = "/surmise")
public class SurmiseController {
    @Autowired
    private SurmiseService surmiseService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private KnowledgeSpaceService knowledgeSpaceService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveSurmise(@RequestBody SurmiseDTO surmiseDTO, HttpServletRequest httpServletRequest) {
        try {
            Surmise surmise = new Surmise();
            if(surmiseDTO.getProblemId() == 0 || surmiseDTO.getProblemId() == null ||  surmiseDTO.getKnowledgeSpaceId() == null || surmiseDTO.getKnowledgeSpaceId() == 0 ||
            surmiseDTO.getProblems() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            List<Problem> problems = problemService.findAll();
            for (Problem p : problems){
                for( Problem dtoProblem : surmiseDTO.getProblems()){
                    if(p.getId() == dtoProblem.getId())
                    {
                        surmise.getProblems().add(p);
                    }
                }
            }

            Optional<Problem> problem = problemService.findById(surmiseDTO.getProblemId());
            if(problem.isPresent() ) {
                problem.ifPresent(problem1 -> {
                    surmise.setProblemId(problem1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<KnowledgeSpace> knowledgeSpace = knowledgeSpaceService.findById(surmiseDTO.getKnowledgeSpaceId());
            if(knowledgeSpace.isPresent() ) {
                knowledgeSpace.ifPresent(knowledgeSpace1 -> {
                    surmise.setKnowledgeSpaceId(knowledgeSpace1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            surmiseService.save(surmise);
            return new ResponseEntity<>(surmise.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Surmise>> getSurmises() {
        List<Surmise> surmises = surmiseService.findAll();
        if(surmises != null)
        {
            return new ResponseEntity<>(surmises, HttpStatus.OK);
        }
        return new ResponseEntity<>(surmises, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/byProblem/{id}", produces = "application/json")
    public ResponseEntity<List<SurmiseDTO>> getSurmisesByProblem(@PathVariable("id") Integer problemId) {
        Optional<Problem> problem = problemService.findById(problemId);
        List<SurmiseDTO> response = new ArrayList<>();

        if(problem.isPresent())
        {
            List<Surmise> surmises = surmiseService.findByProblemId(problem.get());

            for (Surmise surmise : surmises)
            {
                response.add(new SurmiseDTO(surmise.getId(), surmise.getProblemId().getId(), surmise.getKnowledgeSpaceId().getId(), surmise.getProblems()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/byKnowledgeSpace/{id}", produces = "application/json")
    public ResponseEntity<List<SurmiseDTO>> getSurmisesByKnowledgeSpace(@PathVariable("id") Integer knowledgeSpaceId) {
        Optional<KnowledgeSpace> knowledgeSpace = knowledgeSpaceService.findById(knowledgeSpaceId);
        List<SurmiseDTO> response = new ArrayList<>();

        if(knowledgeSpace.isPresent())
        {
            List<Surmise> surmises = surmiseService.findByKnowledgeSpaceId(knowledgeSpace.get());

            for (Surmise surmise : surmises)
            {
                response.add(new SurmiseDTO(surmise.getId(), surmise.getProblemId().getId(), surmise.getKnowledgeSpaceId().getId(), surmise.getProblems()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{surmiseId}/{problemId}/{knowledgeSpaceId}")
    public ResponseEntity<Void> deleteSurmise( @PathVariable("surmiseId") Integer surmiseId, @PathVariable("problemId") Integer problemId, @PathVariable("knowledgeSpaceId") Integer knowledgeSpaceId) {
        Optional<Surmise> surmise = surmiseService.findById(surmiseId);
        Optional<Problem> problem = problemService.findById(problemId);
        Optional<KnowledgeSpace> knowledgeSpace = knowledgeSpaceService.findById(knowledgeSpaceId);
        if(problem.isPresent() ) {
            problem.ifPresent(problem1 -> {
                problem1.getSurmises().remove(surmise.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        if(knowledgeSpace.isPresent() ) {
            knowledgeSpace.ifPresent(knowledgeSpace1 -> {
                knowledgeSpace1.getSurmises().remove(surmise.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }

        surmiseService.remove(surmiseId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
