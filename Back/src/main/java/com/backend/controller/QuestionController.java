package com.backend.controller;

import com.backend.dto.QuestionDTO;
import com.backend.model.Question;
import com.backend.model.TestType;
import com.backend.service.QuestionService;
import com.backend.service.TestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestTypeService testTypeService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveQuestion(@RequestBody QuestionDTO questionDTO, HttpServletRequest httpServletRequest) {
        try {
            Question question = new Question();
            if(questionDTO.getTestTypeId() == null || questionDTO.getTestTypeId() == 0 || questionDTO.getQuestionText().equals("") || questionDTO.getScore() == null|| questionDTO.getScore() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            question.setQuestionText(questionDTO.getQuestionText());
            question.setScore(questionDTO.getScore());

            Optional<TestType> testType = testTypeService.findById(questionDTO.getTestTypeId());
            if(testType.isPresent() ) {
                testType.ifPresent(testType1 -> {
                    question.setTestTypeId(testType1);
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        questionService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
