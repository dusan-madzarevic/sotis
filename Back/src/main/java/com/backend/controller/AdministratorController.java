package com.backend.controller;

import com.backend.dto.AdministratorDTO;
import com.backend.model.Administrator;
import com.backend.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/administrator")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Integer> saveAdministrator(@RequestBody AdministratorDTO administratorDTO, HttpServletRequest httpServletRequest) {
        try {
            Administrator administrator = new Administrator();
            if(administratorDTO.getName().equals("") || administratorDTO.getLastName().equals("") || administratorDTO.getUsername().equals("") || administratorDTO.getEmail().equals("")  || administratorDTO.getPassword().equals(""))
            {
                return new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
            }
            administrator.setName(administratorDTO.getName());
            administrator.setLastName(administratorDTO.getLastName());
            administrator.setUsername(administratorDTO.getUsername());
            administrator.setEmail(administratorDTO.getEmail());
            administrator.setPassword(administratorDTO.getPassword());

            administratorService.save(administrator);
            return new ResponseEntity<>(administrator.getId(), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(0,HttpStatus.NOT_MODIFIED);
        }

    }
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Administrator>> getAdministrators() {
        List<Administrator> administrators = administratorService.findAllByUserType("Administrator");
        if(administrators != null)
        {
            return new ResponseEntity<>(administrators, HttpStatus.OK);
        }
        return new ResponseEntity<>(administrators, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAdministrator(@PathVariable Integer id) {
        administratorService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
