package com.backend.service;

import com.backend.model.Administrator;
import com.backend.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    private AdministratorRepository administratorRepository;

    public Administrator save(Administrator admin) {
        return administratorRepository.save(admin);
    }

    public void remove(Integer id) {
        administratorRepository.deleteById(id);
    }

    public List<Administrator> findAllByUserType(String userType) {
        return administratorRepository.findAllByUserType(userType); }
}
