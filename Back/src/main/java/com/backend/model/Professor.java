package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Professor")
public class Professor extends User {
    @OneToMany(mappedBy = "professorId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Test> tests;

    @ManyToMany(mappedBy = "professors")
    @JsonIgnore
    Set<Subject> subjects;

    public Professor(){}

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }
}
