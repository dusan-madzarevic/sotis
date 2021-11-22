package com.backend.dto;

import java.util.Set;

public class SubjectProfessorDTO {
    private Integer subjectId;
    private Set<Integer> professors;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Set<Integer> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Integer> professors) {
        this.professors = professors;
    }
}
