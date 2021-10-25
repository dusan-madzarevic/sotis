package com.backend.controller;

import com.backend.dto.StudentDTO;
import com.backend.model.Student;
import com.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveStudent(@RequestBody StudentDTO studentDTO, HttpServletRequest httpServletRequest) {
        try {
            Student student = new Student();
            if(studentDTO.getName().equals("") || studentDTO.getLastName().equals("") || studentDTO.getUsername().equals("") || studentDTO.getEmail().equals("")  || studentDTO.getPassword().equals(""))
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            student.setName(studentDTO.getName());
            student.setLastName(studentDTO.getLastName());
            student.setUsername(studentDTO.getUsername());
            student.setEmail(studentDTO.getEmail());
            student.setPassword(studentDTO.getPassword());

            studentService.save(student);
            return new ResponseEntity<>(student.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.findAllByUserType("Student");
        if(students != null)
        {
            return new ResponseEntity<>(students, HttpStatus.OK);
        }
        return new ResponseEntity<>(students, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
