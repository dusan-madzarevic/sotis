package com.backend.controller;

import com.backend.dto.TestDTO;
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
@RequestMapping(value = "/test")
public class TestAttemptController {
    @Autowired
    private TestAttemptService testService;

    @Autowired
    private TestService testTypeService;

    @Autowired
    private StudentService studentService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTestAttempt(@RequestBody TestDTO testDTO, HttpServletRequest httpServletRequest) {
        try {
            TestAttempt testAttempt = new TestAttempt();
            if(testDTO.getStudentId() == null || testDTO.getStudentId() == 0 || testDTO.getTestTypeId() == null || testDTO.getTestTypeId() == 0 ||
                    testDTO.getStartTime().equals("") || testDTO.getEndTime().equals("") || testDTO.getFinalScore().equals("") || testDTO.getPassed() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            testAttempt.setStartTime(testDTO.getStartTime());
            testAttempt.setEndTime(testDTO.getEndTime());
            testAttempt.setFinalScore(testDTO.getFinalScore());
            testAttempt.setPassed(testDTO.getPassed());

            Optional<Student> student = studentService.findById(testDTO.getStudentId());
            if(student.isPresent() ) {
                student.ifPresent(student1 -> {
                    testAttempt.setStudentId(student1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<Test> testType = testTypeService.findById(testDTO.getTestTypeId());
            if(testType.isPresent() ) {
                testType.ifPresent(testType1 -> {
                    testAttempt.setTestTypeId(testType1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testService.save(testAttempt);
            return new ResponseEntity<>(testAttempt.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TestAttempt>> getTests() {
        List<TestAttempt> testAttempts = testService.findAll();
        if(testAttempts != null)
        {
            return new ResponseEntity<>(testAttempts, HttpStatus.OK);
        }
        return new ResponseEntity<>(testAttempts, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        testService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
