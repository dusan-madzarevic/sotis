package com.backend.dto;

public class TestDTO {
    private Integer id;
    private String username;
    private String title;
    private Integer maxScore;
    private Integer passPercentage;

    public TestDTO(Integer id, String username, String title, Integer maxScore, Integer passPercentage) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.maxScore = maxScore;
        this.passPercentage = passPercentage;
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
