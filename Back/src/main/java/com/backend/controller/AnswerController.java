package com.backend.controller;

import com.backend.dto.AnswerDTO;
import com.backend.dto.QuestionDTO;
import com.backend.model.Answer;
import com.backend.model.Question;
import com.backend.model.Section;
import com.backend.service.AnswerService;
import com.backend.service.QuestionService;
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
            if(answerDTO.getQuestionId() == null || answerDTO.getQuestionId() == 0 || answerDTO.getAnswerText().equals("") || answerDTO.getScore() == null || answerDTO.getCorrect() == null)
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

    @GetMapping(value = "/byQuestion/{id}", produces = "application/json")
    public ResponseEntity<List<AnswerDTO>> getQuestionAnswers(@PathVariable("id") Integer questionId) {
        Optional<Question> question = questionService.findById(questionId);
        List<AnswerDTO> response = new ArrayList<>();

        if(question.isPresent())
        {
            List<Answer> answers = answerService.findByQuestion(question.get());

            for (Answer answer : answers)
            {
                response.add(new AnswerDTO(answer.getId(), questionId, answer.getAnswerText(), answer.getCorrect(), answer.getScore()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{answerId}/{questionId}")
    public ResponseEntity<Void> deleteAnswer( @PathVariable("answerId") Integer answerId, @PathVariable("questionId") Integer questionId) {
        Optional<Question> question = questionService.findById(questionId);
        Optional<Answer> answer = answerService.findById(answerId);
        if(question.isPresent() ) {
            question.ifPresent(question1 -> {
                question1.getAnswers().remove(answer.get());
            });
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        answerService.remove(answerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
