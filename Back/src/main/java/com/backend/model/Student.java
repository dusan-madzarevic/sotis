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

    @OneToMany(mappedBy = "studentId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<KnowledgeState> knowledgeStates;

    @ManyToMany(mappedBy = "learnedProblems")
    @JsonIgnore
    Set<Problem> problems;

    public Student(){}

    @JsonIgnore
    public Set<TestAttempt> getTests() {
        return testAttempts;
    }

    public Set<TestAttempt> getTestAttempts() {
        return testAttempts;
    }

    public void setTestAttempts(Set<TestAttempt> testAttempts) {
        this.testAttempts = testAttempts;
    }


    public Set<KnowledgeState> getKnowledgeStates() {
        return knowledgeStates;
    }

    public void setKnowledgeStates(Set<KnowledgeState> knowledgeStates) {
        this.knowledgeStates = knowledgeStates;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
