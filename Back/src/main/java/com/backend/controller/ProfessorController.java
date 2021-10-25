package com.backend.controller;

import com.backend.dto.ProfessorDTO;
import com.backend.model.Professor;
import com.backend.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/professor")
public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveProfessor(@RequestBody ProfessorDTO professorDTO, HttpServletRequest httpServletRequest) {
        try {
            Professor professor = new Professor();
            if(professorDTO.getName().equals("") || professorDTO.getLastName().equals("") || professorDTO.getUsername().equals("") || professorDTO.getEmail().equals("")  || professorDTO.getPassword().equals(""))
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            professor.setName(professorDTO.getName());
            professor.setLastName(professorDTO.getLastName());
            professor.setUsername(professorDTO.getUsername());
            professor.setEmail(professorDTO.getEmail());
            professor.setPassword(professorDTO.getPassword());

            professorService.save(professor);
            return new ResponseEntity<>(professor.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Professor>> getProfessors() {
        List<Professor> professors = professorService.findAllByUserType("Professor");
        if(professors != null)
        {
            return new ResponseEntity<>(professors, HttpStatus.OK);
        }
        return new ResponseEntity<>(professors, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Integer id) {
        professorService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
