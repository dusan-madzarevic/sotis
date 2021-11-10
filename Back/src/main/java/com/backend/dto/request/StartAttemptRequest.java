package com.backend.dto.request;

public class StartAttemptRequest {

    private String studentUsername;
    private Integer testTypeId;

    public StartAttemptRequest() {
    }

    public StartAttemptRequest(String studentUsername, Integer testTypeId) {
        this.studentUsername = studentUsername;
        this.testTypeId = testTypeId;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public Integer getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(Integer testTypeId) {
        this.testTypeId = testTypeId;
    }
}
