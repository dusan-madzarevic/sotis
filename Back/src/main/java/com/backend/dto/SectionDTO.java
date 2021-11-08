package com.backend.dto;

public class SectionDTO {
    private Integer id;
    private Integer testId;
    private String name;

    public SectionDTO() {
    }

    public SectionDTO(Integer id, Integer testId, String name) {
        this.id = id;
        this.testId = testId;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
