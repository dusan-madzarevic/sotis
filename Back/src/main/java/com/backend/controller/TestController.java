package com.backend.controller;

import com.backend.dto.TestDTO;
import com.backend.model.Professor;
import com.backend.model.Test;
import com.backend.service.ProfessorService;
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
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private ProfessorService professorService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTest(@RequestBody TestDTO testDTO, HttpServletRequest httpServletRequest) {
        try {
            Test test = new Test();
            if(testDTO.getProfessorId() == null || testDTO.getProfessorId() == 0 || testDTO.getTitle().equals("") || testDTO.getMaxScore() == null|| testDTO.getMaxScore() == 0 || testDTO.getPassPercentage() == null || testDTO.getPassPercentage() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            test.setTitle(testDTO.getTitle());
            test.setMaxScore(testDTO.getMaxScore());
            test.setPassPercentage(testDTO.getPassPercentage());

            Optional<Professor> professor = professorService.findById(testDTO.getProfessorId());
            if(professor.isPresent() ) {
                professor.ifPresent(professor1 -> {
                    test.setProfessorId(professor1);
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
