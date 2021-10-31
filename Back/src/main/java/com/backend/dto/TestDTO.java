package com.backend.dto;

public class TestDTO {
    private Integer professorId;
    private String title;
    private Integer maxScore;
    private Integer passPercentage;

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getPassPercentage() {
        return passPercentage;
    }

    public void setPassPercentage(Integer passPercentage) {
        this.passPercentage = passPercentage;
    }
}
