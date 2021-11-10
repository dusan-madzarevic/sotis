package com.backend.dto;

import java.util.List;

public class AttemptQuestionDTO {

    private int questionID;
    private String questionText;
    private String section;
    private int questionScore;
    private List<AttemptAnswerDTO> answers;

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }

    public List<AttemptAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AttemptAnswerDTO> answers) {
        this.answers = answers;
    }
}
