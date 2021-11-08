package com.backend.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;

@Entity
@DiscriminatorValue("Student")
public class Student extends User {
    @OneToMany(mappedBy = "studentId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TestAttempt> testAttempts;

    public Student(){}

    public Set<TestAttempt> getTests() {
        return testAttempts;
    }

    public void setTests(Set<TestAttempt> testAttempts) {
        this.testAttempts = testAttempts;
    }
}
