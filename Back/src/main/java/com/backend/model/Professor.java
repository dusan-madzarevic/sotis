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

    public Professor(){}

    public Set<Test> getTestTypes() {
        return tests;
    }

    public void setTestTypes(Set<Test> tests) {
        this.tests = tests;
    }
}
