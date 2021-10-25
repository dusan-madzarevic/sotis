package com.backend.controller;

import com.backend.dto.AnswerDTO;
import com.backend.model.Answer;
import com.backend.model.Question;
import com.backend.service.AnswerService;
import com.backend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveAnswer(@RequestBody AnswerDTO answerDTO, HttpServletRequest httpServletRequest) {
        try {
            Answer answer = new Answer();
            if(answerDTO.getQuestionId() == null || answerDTO.getQuestionId() == 0 || answerDTO.getAnswerText().equals("") || answerDTO.getScore() == null|| answerDTO.getScore() == 0 || answerDTO.getCorrect() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            answer.setAnswerText(answerDTO.getAnswerText());
            answer.setScore(answerDTO.getScore());
            answer.setCorrect(answerDTO.getCorrect());

            Optional<Question> question = questionService.findById(answerDTO.getQuestionId());
            if(question.isPresent() ) {
                question.ifPresent(question1 -> {
                    answer.setQuestionId(question1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            answerService.save(answer);
            return new ResponseEntity<>(answer.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Answer>> getAnswers() {
        List<Answer> answers = answerService.findAll();
        if(answers != null)
        {
            return new ResponseEntity<>(answers, HttpStatus.OK);
        }
        return new ResponseEntity<>(answers, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Integer id) {
        answerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}