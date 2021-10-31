package com.backend.controller;

import com.backend.dto.TestTypeDTO;
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
@RequestMapping(value = "/testType")
public class TestController {
    @Autowired
    private TestService testTypeService;

    @Autowired
    private ProfessorService professorService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTestType(@RequestBody TestTypeDTO testTypeDTO, HttpServletRequest httpServletRequest) {
        try {
            Test test = new Test();
            if(testTypeDTO.getProfessorId() == null || testTypeDTO.getProfessorId() == 0 || testTypeDTO.getTitle().equals("") || testTypeDTO.getMaxScore() == null|| testTypeDTO.getMaxScore() == 0 || testTypeDTO.getPassPercentage() == null || testTypeDTO.getPassPercentage() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            test.setTitle(testTypeDTO.getTitle());
            test.setMaxScore(testTypeDTO.getMaxScore());
            test.setPassPercentage(testTypeDTO.getPassPercentage());

            Optional<Professor> professor = professorService.findById(testTypeDTO.getProfessorId());
            if(professor.isPresent() ) {
                professor.ifPresent(professor1 -> {
                    test.setProfessorId(professor1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testTypeService.save(test);
            return new ResponseEntity<>(test.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Test>> getTestTypes() {
        List<Test> tests = testTypeService.findAll();
        if(tests != null)
        {
            return new ResponseEntity<>(tests, HttpStatus.OK);
        }
        return new ResponseEntity<>(tests, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTestType(@PathVariable Integer id) {
        testTypeService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
