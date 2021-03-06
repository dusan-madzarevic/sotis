package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subjectId")
    private Subject subject;

    @OneToMany(mappedBy = "problemId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Surmise> surmises;

    @ManyToMany
    @JoinTable(
            name = "learnedProblems",
            joinColumns = @JoinColumn(name = "problemId"),
            inverseJoinColumns = @JoinColumn(name = "studentId"))
    @JsonIgnore
    private Set<Student> learnedProblems;

    @ManyToMany
    @JoinTable(
            name = "question_problems",
            joinColumns = @JoinColumn(name = "problemId"),
            inverseJoinColumns = @JoinColumn(name = "questionId"))
    @JsonIgnore
    Set<Question> questions;

    @ManyToMany(mappedBy = "problems")
    private List<KnowledgeState> knowledgeStates;

    public Problem() {
    }

    public Problem(String name, String description, Subject subject) {
        this.name = name;
        this.description = description;
        this.subject = subject;
    }

    public Problem(Integer id, String name, String description, Set<Question> questions, Subject subject) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.subject = subject;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Surmise> getSurmises() {
        return surmises;
    }

    public void setSurmises(Set<Surmise> surmises) {
        this.surmises = surmises;
    }

    @JsonIgnore
    public Set<Student> getLearnedProblems() {
        return learnedProblems;
    }

    public void setLearnedProblems(Set<Student> learnedProblems) {
        this.learnedProblems = learnedProblems;
    }

    public List<KnowledgeState> getKnowledgeStates() {
        return knowledgeStates;
    }

    public void setKnowledgeStates(List<KnowledgeState> knowledgeStates) {
        this.knowledgeStates = knowledgeStates;
    }
}
