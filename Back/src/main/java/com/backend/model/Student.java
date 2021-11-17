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

    @OneToMany(mappedBy = "studentId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)   //ovo proveriti jer ne postoji u bazi studentId vec userId
    @JsonIgnore
    private Set<StudentSubject> studentSubjects;

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

    public Set<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(Set<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
}
