package com.backend.dto;

import java.util.List;

public class LearnedProblemDTO {
    private List<Integer> studentId;
    private Integer problemId;

    public List<Integer> getStudentId() {
        return studentId;
    }

    public void setStudentId(List<Integer> studentId) {
        this.studentId = studentId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
}
