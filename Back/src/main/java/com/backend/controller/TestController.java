package com.backend.controller;

import com.backend.dto.QuestionDTO;
import com.backend.dto.TestDTO;
import com.backend.model.*;
import com.backend.service.ProfessorService;
import com.backend.service.TestService;
import com.backend.service.UserService;
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
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveTest(@RequestBody TestDTO testDTO, HttpServletRequest httpServletRequest) {
        try {
            Test test = new Test();
            if(testDTO.getUsername() == null || testDTO.getUsername().equals("") || testDTO.getTitle().equals("") || testDTO.getMaxScore() == null|| testDTO.getMaxScore() == 0 || testDTO.getPassPercentage() == null || testDTO.getPassPercentage() == 0)
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            test.setTitle(testDTO.getTitle());
            test.setMaxScore(testDTO.getMaxScore());
            test.setPassPercentage(testDTO.getPassPercentage());

            Professor professor = new Professor();

            List<Professor> professors = professorService.findAllByUserType("Professor");
            for (Professor p : professors){
                if(p.getUsername().equals(testDTO.getUsername()))
                    professor = p;
            }

            if(professor!= null){
                test.setProfessorId(professor);
            } else{
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }

            testService.save(test);
            return new ResponseEntity<>(test.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<TestDTO>> getTests() {
        List<Test> tests = testService.findAll();
        List<TestDTO> response = new ArrayList<>();
        for (Test test :
                tests) {
            response.add(new TestDTO(test.getId(), test.getProfessorId().getUsername(), test.getProfessorId().getName()+ " "+ test.getProfessorId().getLastName(), test.getTitle(), test.getMaxScore(), test.getPassPercentage()));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping(value = "/byProfessor/{username}", produces = "application/json")
    public ResponseEntity<List<TestDTO>> getProfessorTests(@PathVariable("username") String professorUsername) {
        List<User> users = userService.findAll();
        List<Test> tests = testService.findAll();
        List<TestDTO> response = new ArrayList<>();

        if(!users.isEmpty()){
            for( User u : users){
                if(u.getUsername().equals(professorUsername)){
                    for( Test t : tests){
                        if(t.getProfessorId().getUsername().equals(professorUsername)){
                            response.add(new TestDTO(t.getId(), professorUsername, t.getProfessorId().getName()+ " "+ t.getProfessorId().getLastName(), t.getTitle(), t.getMaxScore(), t.getPassPercentage()));
                        }
                    }
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            return new ResponseEntity<List<TestDTO>>(response, HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Integer id) {
        List<Professor> professors = professorService.findAllByUserType("Professor");
        Optional<Test> test = testService.findById(id);
        for(Professor p : professors){
            if(test.isPresent() ) {
                test.ifPresent(student1 -> {
                    p.getTests().remove(student1);
                });
            }
        }
        testService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
