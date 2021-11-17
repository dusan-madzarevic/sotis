package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class KnowledgeState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "previousKnowledgeStateId")
    private KnowledgeState prednodni;
    @OneToMany(mappedBy = "prednodni",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<KnowledgeState> predhodnici;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nextKnowledgeStateId")
    private KnowledgeState sledeci;
    @OneToMany(mappedBy = "sledeci",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<KnowledgeState> sledbenici;

    @OneToMany(mappedBy = "knowledgeStateId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Problem> problems;

    @OneToMany(mappedBy = "knowledgeStateId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<StudentSubject> studentSubjects;


    public KnowledgeState() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KnowledgeState getPrednodni() {
        return prednodni;
    }

    public void setPrednodni(KnowledgeState prednodni) {
        this.prednodni = prednodni;
    }

    public Set<KnowledgeState> getPredhodnici() {
        return predhodnici;
    }

    public void setPredhodnici(Set<KnowledgeState> predhodnici) {
        this.predhodnici = predhodnici;
    }

    public KnowledgeState getSledeci() {
        return sledeci;
    }

    public void setSledeci(KnowledgeState sledeci) {
        this.sledeci = sledeci;
    }

    public Set<KnowledgeState> getSledbenici() {
        return sledbenici;
    }

    public void setSledbenici(Set<KnowledgeState> sledbenici) {
        this.sledbenici = sledbenici;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    public Set<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(Set<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
}
