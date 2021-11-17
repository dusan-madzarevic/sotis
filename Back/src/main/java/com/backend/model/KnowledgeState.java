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
    private KnowledgeState previous;
    @OneToMany(mappedBy = "previous",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<KnowledgeState> predecessors;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nextKnowledgeStateId")
    private KnowledgeState next;
    @OneToMany(mappedBy = "next",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<KnowledgeState> followers;

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

    public KnowledgeState getPrevious() {
        return previous;
    }

    public void setPrevious(KnowledgeState previous) {
        this.previous = previous;
    }

    public KnowledgeState getNext() {
        return next;
    }

    public void setNext(KnowledgeState next) {
        this.next = next;
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

    public Set<KnowledgeState> getPredecessors() {
        return predecessors;
    }

    public void setPredecessors(Set<KnowledgeState> predecessors) {
        this.predecessors = predecessors;
    }

    public Set<KnowledgeState> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<KnowledgeState> followers) {
        this.followers = followers;
    }
}
