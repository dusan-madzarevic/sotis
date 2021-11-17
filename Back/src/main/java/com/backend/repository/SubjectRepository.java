package com.backend.repository;

import com.backend.model.Subject;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SubjectRepository extends  JpaRepository<Subject, Integer>{
}
