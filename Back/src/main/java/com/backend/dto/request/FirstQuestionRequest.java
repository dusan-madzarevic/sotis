package com.backend.dto.request;

public class FirstQuestionRequest {

    private String studentUsername;
    private Integer knowledgeSpaceId;

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
