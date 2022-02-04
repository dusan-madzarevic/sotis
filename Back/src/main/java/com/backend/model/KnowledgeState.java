package com.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class KnowledgeState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student studentId;
    @ManyToMany
    @JoinTable(
            name = "knowledge_state_problems",
            joinColumns = @JoinColumn(name = "knowledge_state_id"),
            inverseJoinColumns = @JoinColumn(name = "problem_id"))
    private List<Problem> problems;
    @Column(name = "finalState")
    private Boolean finalState;
    @Column(name = "likelihood")
    private Double likelihood;

    public KnowledgeState() {
        problems = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public Boolean getFinalState() {
        return finalState;
    }

    public void setFinalState(Boolean finalState) {
        this.finalState = finalState;
    }

    public Double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(Double likelihood) {
        this.likelihood = likelihood;
    }
}
