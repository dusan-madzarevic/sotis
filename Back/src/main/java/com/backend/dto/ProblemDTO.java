package com.backend.dto;

public class ProblemDTO {
    private Integer id;
    private Integer subjectId;
    private String name;
    private String description;

    public ProblemDTO() {
    }

    public ProblemDTO(Integer id, String name, String description, Integer subjectId) {
        this.id = id;
        this.subjectId = subjectId;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
