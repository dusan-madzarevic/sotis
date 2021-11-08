package com.backend.dto;

public class QuestionDTO {
    private Integer id;
    private Integer sectionId;
    private String questionText;
    private Integer score;

    public QuestionDTO(Integer id, Integer sectionId, String questionText, Integer score) {
        this.id = id;
        this.sectionId = sectionId;
        this.questionText = questionText;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
