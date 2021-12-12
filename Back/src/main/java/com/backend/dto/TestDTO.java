package com.backend.dto;

public class TestDTO {
    private Integer id;
    private String username;
    private String professorName;
    private String title;
    private Integer maxScore;
    private Integer passPercentage;
    private Integer subjectId;

    public TestDTO(Integer id, String username, String professorName, String title, Integer maxScore, Integer passPercentage, Integer subjectId) {
        this.id = id;
        this.username = username;
        this.professorName = professorName;
        this.title = title;
        this.maxScore = maxScore;
        this.passPercentage = passPercentage;
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
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

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
