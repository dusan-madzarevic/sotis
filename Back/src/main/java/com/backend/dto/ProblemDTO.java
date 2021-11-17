package com.backend.dto;

public class ProblemDTO {

    private Integer subjectId;

    private String name;

    private String description;

    private Integer knowledgeStateId;

    public ProblemDTO(String name, String description, Integer id, Integer knowledgeStateId) {
        this.subjectId = id;
        this.name = name;
        this.description = description;
        this.knowledgeStateId = knowledgeStateId;
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

    public Integer getKnowledgeStateId() {
        return knowledgeStateId;
    }

    public void setKnowledgeStateId(Integer knowledgeStateId) {
        this.knowledgeStateId = knowledgeStateId;
    }
}
