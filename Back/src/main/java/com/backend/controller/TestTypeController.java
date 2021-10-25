package com.backend.controller;

import com.backend.dto.TestTypeDTO;
import com.backend.model.Professor;
import com.backend.model.TestType;
import com.backend.service.ProfessorService;
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
@RequestMapping(value = "/testType")
public class TestTypeController {
    @Autowired
    private TestTypeService testTypeService;

    @Autowired
    private ProfessorService professorService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTestType(@RequestBody TestTypeDTO testTypeDTO, HttpServletRequest httpServletRequest) {
        try {
            TestType testType = new TestType();
            if(testTypeDTO.getProfessorId() == null || testTypeDTO.getProfessorId() == 0 || testTypeDTO.getTitle().equals("") || testTypeDTO.getMaxScore() == null|| testTypeDTO.getMaxScore() == 0 || testTypeDTO.getPassPercentage() == null || testTypeDTO.getPassPercentage() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            testType.setTitle(testTypeDTO.getTitle());
            testType.setMaxScore(testTypeDTO.getMaxScore());
            testType.setPassPercentage(testTypeDTO.getPassPercentage());

            Optional<Professor> professor = professorService.findById(testTypeDTO.getProfessorId());
            if(professor.isPresent() ) {
                professor.ifPresent(professor1 -> {
                    testType.setProfessorId(professor1);
                });
            }
            else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testTypeService.save(testType);
            return new ResponseEntity<>(testType.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TestType>> getTestTypes() {
        List<TestType> testTypes = testTypeService.findAll();
        if(testTypes != null)
        {
            return new ResponseEntity<>(testTypes, HttpStatus.OK);
        }
        return new ResponseEntity<>(testTypes, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTestType(@PathVariable Integer id) {
        testTypeService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
