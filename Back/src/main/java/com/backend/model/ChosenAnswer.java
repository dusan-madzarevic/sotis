package com.backend.model;

import javax.persistence.*;

@Entity
public class ChosenAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testId")
    private Test testId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answerId")
    private Answer answerId;

    public ChosenAnswer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
    }

    public Answer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Answer answerId) {
        this.answerId = answerId;
    }
}
