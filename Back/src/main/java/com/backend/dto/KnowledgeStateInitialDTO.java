package com.backend.dto;

public class KnowledgeStateInitialDTO {
    private String studentUsername;
    private Integer knowledgeSpaceId;

    public KnowledgeStateInitialDTO(String studentUsername, Integer knowledgeSpaceId) {
        this.studentUsername = studentUsername;
        this.knowledgeSpaceId = knowledgeSpaceId;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public Integer getKnowledgeSpaceId() {
        return knowledgeSpaceId;
    }

    public void setKnowledgeSpaceId(Integer knowledgeSpaceId) {
        this.knowledgeSpaceId = knowledgeSpaceId;
    }
}
