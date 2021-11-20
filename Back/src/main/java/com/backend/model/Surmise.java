package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Surmise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "knowledgeSpaceId")
    private KnowledgeSpace knowledgeSpaceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "problemId")
    private Problem problemId;

    @OneToMany(mappedBy = "surmiseId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Problem> problems;

    public Surmise() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KnowledgeSpace getKnowledgeSpaceId() {
        return knowledgeSpaceId;
    }

    public void setKnowledgeSpaceId(KnowledgeSpace knowledgeSpaceId) {
        this.knowledgeSpaceId = knowledgeSpaceId;
    }

    public Problem getProblemId() {
        return problemId;
    }

    public void setProblemId(Problem problemId) {
        this.problemId = problemId;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
