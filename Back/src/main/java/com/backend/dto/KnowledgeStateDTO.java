package com.backend.dto;

public class KnowledgeStateDTO {
    private Integer id;
    private Integer studentId;
    private Integer surmiseId;
    private Boolean finalState;
    private Integer likelihood;

    public KnowledgeStateDTO(Integer id, Integer studentId, Integer surmiseId, Boolean finalState, Integer likelihood) {
        this.id = id;
        this.studentId = studentId;
        this.surmiseId = surmiseId;
        this.finalState = finalState;
        this.likelihood = likelihood;
    }

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

    public Integer getSurmiseId() {
        return surmiseId;
    }

    public void setSurmiseId(Integer surmiseId) {
        this.surmiseId = surmiseId;
    }

    public Boolean getFinalState() {
        return finalState;
    }

    public void setFinalState(Boolean finalState) {
        this.finalState = finalState;
    }

    public Integer getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(Integer likelihood) {
        this.likelihood = likelihood;
    }
}
