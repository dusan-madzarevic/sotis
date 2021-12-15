package com.backend.dto;

import java.util.ArrayList;
import java.util.List;

public class TestAttemptIITADto {

    private String studentName;
    private List<Integer> answers;

    public TestAttemptIITADto() {
        answers = new ArrayList<>();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }
}
