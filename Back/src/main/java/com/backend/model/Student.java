package com.backend.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Entity
@DiscriminatorValue("Student")
public class Student extends User {
    @OneToMany(mappedBy = "studentId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Test> tests;

    public Student(){}

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
