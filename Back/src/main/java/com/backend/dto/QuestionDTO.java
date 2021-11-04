package com.backend.dto;

import org.springframework.validation.annotation.Validated;

public class QuestionDTO {
    private Integer sectionId;
    private String questionText;
    private Integer score;

    public QuestionDTO(Integer sectionId, String questionText, Integer score) {
        this.sectionId = sectionId;
        this.questionText = questionText;
        this.score = score;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
