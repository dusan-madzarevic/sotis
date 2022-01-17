package com.backend.dto;

public class KnowledgeStateInitialDTO {
    private Integer studentId;
    private Integer knowledgeSpaceId;

    public KnowledgeStateInitialDTO(Integer studentId, Integer knowledgeSpaceId) {
        this.studentId = studentId;
        this.knowledgeSpaceId = knowledgeSpaceId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getKnowledgeSpaceId() {
        return knowledgeSpaceId;
    }

    public void setKnowledgeSpaceId(Integer knowledgeSpaceId) {
        this.knowledgeSpaceId = knowledgeSpaceId;
    }
}
