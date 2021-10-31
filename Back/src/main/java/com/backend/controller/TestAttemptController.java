package com.backend.controller;

import com.backend.dto.TestAttemptDTO;
import com.backend.model.*;
import com.backend.service.StudentService;
import com.backend.service.TestAttemptService;
import com.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = "/testAttempt")
public class TestAttemptController {
    @Autowired
    private TestAttemptService testAttemptService;

    @Autowired
    private TestService testService;

    @Autowired
    private StudentService studentService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTestAttempt(@RequestBody TestAttemptDTO testAttemptDTO, HttpServletRequest httpServletRequest) {
        try {
            TestAttempt testAttempt = new TestAttempt();
            if(testAttemptDTO.getStudentId() == null || testAttemptDTO.getStudentId() == 0 || testAttemptDTO.getTestTypeId() == null || testAttemptDTO.getTestTypeId() == 0 ||
                    testAttemptDTO.getStartTime().equals("") || testAttemptDTO.getEndTime().equals("") || testAttemptDTO.getFinalScore().equals("") || testAttemptDTO.getPassed() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            testAttempt.setStartTime(testAttemptDTO.getStartTime());
            testAttempt.setEndTime(testAttemptDTO.getEndTime());
            testAttempt.setFinalScore(testAttemptDTO.getFinalScore());
            testAttempt.setPassed(testAttemptDTO.getPassed());

            Optional<Student> student = studentService.findById(testAttemptDTO.getStudentId());
            if(student.isPresent() ) {
                student.ifPresent(student1 -> {
                    testAttempt.setStudentId(student1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<Test> testType = testService.findById(testAttemptDTO.getTestTypeId());
            if(testType.isPresent() ) {
                testType.ifPresent(testType1 -> {
                    testAttempt.setTestTypeId(testType1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testAttemptService.save(testAttempt);
            return new ResponseEntity<>(testAttempt.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TestAttempt>> getTestAttempts() {
        List<TestAttempt> testAttempts = testAttemptService.findAll();
        if(testAttempts != null)
        {
            return new ResponseEntity<>(testAttempts, HttpStatus.OK);
        }
        return new ResponseEntity<>(testAttempts, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTestAttempt(@PathVariable Integer id) {
        testAttemptService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
