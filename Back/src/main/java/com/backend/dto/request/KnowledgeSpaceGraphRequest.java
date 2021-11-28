package com.backend.dto.request;

import com.backend.dto.ProblemDTO;
import com.backend.dto.SurmiseLinkDTO;

import java.util.List;

public class KnowledgeSpaceGraphRequest {

    private int subjectId;
    private String name;
    private List<ProblemDTO> problems;
    private List<SurmiseLinkDTO> links;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProblemDTO> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemDTO> problems) {
        this.problems = problems;
    }

    public List<SurmiseLinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<SurmiseLinkDTO> links) {
        this.links = links;
    }
}
