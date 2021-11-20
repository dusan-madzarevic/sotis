package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "subject",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Problem> problems;

    @OneToMany(mappedBy = "subjectId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<KnowledgeSpace> knowledgeSpaces;

    public Subject() {
    }

    public Subject(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }

    public Set<KnowledgeSpace> getKnowledgeSpaces() {
        return knowledgeSpaces;
    }

    public void setKnowledgeSpaces(Set<KnowledgeSpace> knowledgeSpaces) {
        this.knowledgeSpaces = knowledgeSpaces;
    }
}
