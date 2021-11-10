package com.backend.dto;

import java.util.List;

public class StartAttemptDTO {

    private int attemptID;
    private String testName;
    private List<AttemptQuestionDTO> questions;

    public int getAttemptID() {
        return attemptID;
    }

    public void setAttemptID(int attemptID) {
        this.attemptID = attemptID;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public List<AttemptQuestionDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<AttemptQuestionDTO> questions) {
        this.questions = questions;
    }
}
