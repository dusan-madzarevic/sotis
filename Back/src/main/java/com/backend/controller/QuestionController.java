package com.backend.controller;

import com.backend.dto.QuestionDTO;
import com.backend.dto.SectionDTO;
import com.backend.model.Question;
import com.backend.model.Section;
import com.backend.model.Test;
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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveQuestion(@RequestParam("imgFile") MultipartFile imgFile, @RequestBody QuestionDTO questionDTO, HttpServletRequest httpServletRequest) {
        try {
            Question question = new Question();
            if(questionDTO.getQuestionText().equals("") || questionDTO.getScore() == null|| questionDTO.getScore() == 0)
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

            byte[] img = imgFile.getBytes();

            question.setQuestionImage(img);

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
    public ResponseEntity<List<QuestionDTO>> getSectionQuestions(@PathVariable("id") Integer sectionId) {
        Optional<Section> section = sectionService.findById(sectionId);
        List<QuestionDTO> response = new ArrayList<>();

        if(section.isPresent())
        {
            List<Question> questions = questionService.findBySection(section.get());

            for (Question question : questions)
            {
                response.add(new QuestionDTO(sectionId, question.getQuestionText(), question.getScore()));
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        questionService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
