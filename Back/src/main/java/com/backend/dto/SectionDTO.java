package com.backend.dto;

public class SectionDTO {
    private Integer testId;
    private String name;

    public SectionDTO() {
    }

    public SectionDTO(Integer testId, String name) {
        this.testId = testId;
        this.name = name;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
