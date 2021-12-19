package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class TestAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student studentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testId")
    private Test testId;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "endTime")
    private String endTime;
    @Column(name = "finalScore")
    private String finalScore;
    @Column(name = "passed")
    private Boolean passed;

    @ManyToMany
    @JoinTable(
            name = "chosen_answers",
            joinColumns = @JoinColumn(name = "testAttemptId"),
            inverseJoinColumns = @JoinColumn(name = "answerId"))
    @JsonIgnore
    private Set<Answer> chosenAnswers;

    public TestAttempt() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    @JsonIgnore
    public Set<Answer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(Set<Answer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }
}
