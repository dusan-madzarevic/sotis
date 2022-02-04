package com.backend.dto;

public class AnswerDTO {
    private Integer id;
    private String studentUsername;
    private Integer questionId;
    private String answerText;
    private Boolean correct;
    private Integer score;

    public AnswerDTO(Integer id, Integer questionId, String answerText, Boolean correct, Integer score) {
        this.id = id;
        this.questionId = questionId;
        this.answerText = answerText;
        this.correct = correct;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }
}
