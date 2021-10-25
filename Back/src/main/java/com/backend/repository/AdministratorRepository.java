package com.backend.repository;

import com.backend.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    Administrator save(Administrator administrator);
    void deleteById(Integer id);
    List<Administrator> findAllByUserType(String userType);
}
