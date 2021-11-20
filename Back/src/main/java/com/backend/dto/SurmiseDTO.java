package com.backend.dto;

import com.backend.model.Problem;

import java.util.Set;

public class SurmiseDTO {
    private Integer id;
    private Integer knowledgeSpaceId;
    private Integer problemId;
    private Set<Problem> problems;

    public SurmiseDTO(Integer id,Integer problemId, Integer knowledgeSpaceId, Set<Problem> problems) {
        this.id = id;
        this.knowledgeSpaceId = knowledgeSpaceId;
        this.problemId = problemId;
        this.problems = problems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKnowledgeSpaceId() {
        return knowledgeSpaceId;
    }

    public void setKnowledgeSpaceId(Integer knowledgeSpaceId) {
        this.knowledgeSpaceId = knowledgeSpaceId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Set<Problem> getProblems() {
        return problems;
    }

    public void setProblems(Set<Problem> problems) {
        this.problems = problems;
    }
}
