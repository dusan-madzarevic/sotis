package com.backend.dto;

import java.util.List;

public class ProblemQuestionsDTO {
    private Integer problemId;
    private List<Integer> questions;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public List<Integer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }
}
