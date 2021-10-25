package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "studentId")
    private Student studentId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testTypeId")
    private TestType testTypeId;
    @Column(name = "startTime")
    private String startTime;
    @Column(name = "endTime")
    private String endTime;
    @Column(name = "finalScore")
    private String finalScore;
    @Column(name = "passed")
    private Boolean passed;

    @OneToMany(mappedBy = "testId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<ChosenAnswer> chosenAnswers;

    public Test() {
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

    public TestType getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(TestType testTypeId) {
        this.testTypeId = testTypeId;
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

    public Set<ChosenAnswer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(Set<ChosenAnswer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }
}
