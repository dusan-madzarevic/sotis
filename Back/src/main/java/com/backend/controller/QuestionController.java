package com.backend.controller;

import com.backend.dto.QuestionDTO;
import com.backend.dto.SectionDTO;
import com.backend.dto.SurmiseDTO;
import com.backend.model.*;
import com.backend.service.ProblemService;
import com.backend.service.QuestionService;
import com.backend.service.SectionService;
import com.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private ProblemService problemService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveQuestion(@RequestBody QuestionDTO questionDTO, HttpServletRequest httpServletRequest) {
        try {
            Question question = new Question();
            if(questionDTO.getQuestionText().equals("") || questionDTO.getScore() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            question.setQuestionText(questionDTO.getQuestionText());
            question.setScore(questionDTO.getScore());

            Optional<Section> section = sectionService.findById(questionDTO.getSectionId());

            if(section.isPresent() ) {
                section.ifPresent(section1 -> {
                    question.setSectionId(section1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            questionService.save(question);
            return new ResponseEntity<>(question.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Question>> getQuestion() {
        List<Question> questions = questionService.findAll();
        if(questions != null)
        {
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }
        return new ResponseEntity<>(questions, HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/bySection/{id}", produces = "application/json")
    public ResponseEntity<List<Question>> getSectionQuestions(@PathVariable("id") Integer sectionId) {
        Optional<Section> section = sectionService.findById(sectionId);

        if(section.isPresent())
        {
            List<Question> questions = questionService.findBySection(section.get());
            return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @GetMapping(value = "/byProblem/{id}", produces = "application/json")
    public ResponseEntity<List<Question>> getQuestionsByProblem(@PathVariable("id") Integer problemId) {
        Optional<Problem> problem = problemService.findById(problemId);

        if(problem.isPresent())
        {
            List<Question> questions = questionService.findByProblemId(problem.get());
            return new ResponseEntity<>(questions, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{questionId}/{sectionId}/{problemId}")
    public ResponseEntity<Void> deleteQuestion( @PathVariable("questionId") Integer questionId, @PathVariable("sectionId") Integer sectionId, @PathVariable("problemId") Integer problemId) {
        Optional<Section> section = sectionService.findById(sectionId);
        Optional<Problem> problem = problemService.findById(problemId);
        Optional<Question> question = questionService.findById(questionId);
        if(section.isPresent() ) {
            section.ifPresent(section1 -> {
                section1.getQuestions().remove(question.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        if(problem.isPresent() ) {
            problem.ifPresent(problem1 -> {
                problem1.getQuestions().remove(question.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        questionService.remove(questionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
