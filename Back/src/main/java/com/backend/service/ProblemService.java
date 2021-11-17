package com.backend.service;

import com.backend.model.Problem;
import com.backend.model.Subject;
import com.backend.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public Problem save(Problem problem) {
        return problemRepository.save(problem);
    }

    public void remove(Integer id) {
        problemRepository.deleteById(id);
    }

    public Optional<Problem> findById(Integer id) { return  problemRepository.findById(id); }

    public List<Problem> findBySubject(Subject subject){ return  problemRepository.findBySubject(subject);}

}
