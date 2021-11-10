package com.backend.dto.request;

import com.backend.dto.ChosenAnswerDTO;

import java.util.List;

public class SubmitAttemptRequest {

    private Integer testAttemptId;
    private List<ChosenAnswerDTO> answers;

    public Integer getTestAttemptId() {
        return testAttemptId;
    }

    public void setTestAttemptId(Integer testAttemptId) {
        this.testAttemptId = testAttemptId;
    }

    public List<ChosenAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ChosenAnswerDTO> answers) {
        this.answers = answers;
    }
}
