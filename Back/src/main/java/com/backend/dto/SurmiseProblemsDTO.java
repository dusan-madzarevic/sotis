package com.backend.dto;

import java.util.List;

public class SurmiseProblemsDTO {
    private Integer surmiseId;
    private List<Integer> problems;

    public Integer getSurmiseId() {
        return surmiseId;
    }

    public void setSurmiseId(Integer surmiseId) {
        this.surmiseId = surmiseId;
    }

    public List<Integer> getProblems() {
        return problems;
    }

    public void setProblems(List<Integer> problems) {
        this.problems = problems;
    }
}
