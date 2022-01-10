package com.backend.model;

import javax.persistence.*;

@Entity
public class KnowledgeState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student studentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surmiseId")
    private Surmise surmiseId;
    @Column(name = "finalState")
    private Boolean finalState;
    @Column(name = "likelihood")
    private Double likelihood;

    public KnowledgeState() {
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

    public Surmise getSurmiseId() {
        return surmiseId;
    }

    public void setSurmiseId(Surmise surmiseId) {
        this.surmiseId = surmiseId;
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
