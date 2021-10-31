package com.backend.controller;

import com.backend.dto.ChosenAnswerDTO;
import com.backend.model.Answer;
import com.backend.model.ChosenAnswer;
import com.backend.model.TestAttempt;
import com.backend.service.AnswerService;
import com.backend.service.ChosenAnswerService;
import com.backend.service.TestAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/chosenAnswer")
public class ChosenAnswerController {
    @Autowired
    private ChosenAnswerService chosenAnswerService;

    @Autowired
    private TestAttemptService testService;

    @Autowired
    private AnswerService answerService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveChosenAnswer(@RequestBody ChosenAnswerDTO chosenAnswerDTO, HttpServletRequest httpServletRequest) {
        try {
            ChosenAnswer chosenAnswer = new ChosenAnswer();
            if(chosenAnswerDTO.getTestId() == null || chosenAnswerDTO.getTestId() == 0 || chosenAnswerDTO.getAnswerId() == null || chosenAnswerDTO.getAnswerId() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<TestAttempt> test = testService.findById(chosenAnswerDTO.getTestId());
            if(test.isPresent() ) {
                test.ifPresent(test1 -> {
                    chosenAnswer.setTestId(test1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<Answer> answer = answerService.findById(chosenAnswerDTO.getAnswerId());
            if(answer.isPresent() ) {
                answer.ifPresent(answer1 -> {
                    chosenAnswer.setAnswerId(answer1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            chosenAnswerService.save(chosenAnswer);
            return new ResponseEntity<>(chosenAnswer.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ChosenAnswer>> getChosenAnswers() {
        List<ChosenAnswer> chosenAnswers = chosenAnswerService.findAll();
        if(chosenAnswers != null)
        {
            return new ResponseEntity<>(chosenAnswers, HttpStatus.OK);
        }
        return new ResponseEntity<>(chosenAnswers, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteChosenAnswer(@PathVariable Integer id) {
        chosenAnswerService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
