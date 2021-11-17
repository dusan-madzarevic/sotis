package com.backend.dto;

public class StudentSubjectDTO {
    private Integer id;
    private Integer studentId;
    private Integer subjectId;
    private Integer knowledgeStateId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getKnowledgeStateId() {
        return knowledgeStateId;
    }

    public void setKnowledgeStateId(Integer knowledgeStateId) {
        this.knowledgeStateId = knowledgeStateId;
    }
}
