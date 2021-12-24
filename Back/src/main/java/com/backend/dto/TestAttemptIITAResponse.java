package com.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class TestAttemptIITAResponse {
    private int subjectId;
    private List<Integer> problems = new ArrayList<>();
    private List<TestAttemptIITADto> results;

    public List<TestAttemptIITADto> getResults() {
        return results;
    }

    public void setResults(List<TestAttemptIITADto> results) {
        this.results = results;
    }

    public List<Integer> getProblems() {
        return problems;
    }

    public void setProblems(List<Integer> problems) {
        this.problems = problems;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
