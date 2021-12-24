package com.backend.dto;

import com.backend.model.Problem;

import java.util.HashSet;
import java.util.Set;

public class SurmiseGraphDTO {

    private Integer id;
    private Integer knowledgeSpaceId;
    private ProblemDTO problemFrom;
    private Set<ProblemDTO> problems;

    public SurmiseGraphDTO() {
        problems = new HashSet<>();
    }

    public SurmiseGraphDTO(Integer id, Integer knowledgeSpaceId, ProblemDTO problemFrom, Set<ProblemDTO> problems) {
        this.id = id;
        this.knowledgeSpaceId = knowledgeSpaceId;
        this.problemFrom = problemFrom;
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

    public ProblemDTO getProblemFrom() {
        return problemFrom;
    }

    public void setProblemFrom(ProblemDTO problemFrom) {
        this.problemFrom = problemFrom;
    }

    public Set<ProblemDTO> getProblems() {
        return problems;
    }

    public void setProblems(Set<ProblemDTO> problems) {
        this.problems = problems;
    }
}
