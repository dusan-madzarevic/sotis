package com.backend.controller;

import com.backend.dto.TestDTO;
import com.backend.model.*;
import com.backend.service.StudentService;
import com.backend.service.TestService;
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
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private StudentService studentService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTest(@RequestBody TestDTO testDTO, HttpServletRequest httpServletRequest) {
        try {
            Test test = new Test();
            if(testDTO.getStudentId() == null || testDTO.getStudentId() == 0 || testDTO.getTestTypeId() == null || testDTO.getTestTypeId() == 0 ||
                    testDTO.getStartTime().equals("") || testDTO.getEndTime().equals("") || testDTO.getFinalScore().equals("") || testDTO.getPassed() == null)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            test.setStartTime(testDTO.getStartTime());
            test.setEndTime(testDTO.getEndTime());
            test.setFinalScore(testDTO.getFinalScore());
            test.setPassed(testDTO.getPassed());

            Optional<Student> student = studentService.findById(testDTO.getStudentId());
            if(student.isPresent() ) {
                student.ifPresent(student1 -> {
                    test.setStudentId(student1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            Optional<TestType> testType = testTypeService.findById(testDTO.getTestTypeId());
            if(testType.isPresent() ) {
                testType.ifPresent(testType1 -> {
                    test.setTestTypeId(testType1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testService.save(test);
            return new ResponseEntity<>(test.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Test>> getTests() {
        List<Test> tests = testService.findAll();
        if(tests != null)
        {
            return new ResponseEntity<>(tests, HttpStatus.OK);
        }
        return new ResponseEntity<>(tests, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        testService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
