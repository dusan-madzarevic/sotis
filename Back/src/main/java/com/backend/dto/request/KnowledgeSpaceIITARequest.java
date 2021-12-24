package com.backend.dto.request;

import com.backend.dto.ProblemDTO;
import com.backend.dto.SurmiseLinkDTO;

import java.util.List;

public class KnowledgeSpaceIITARequest {

    private int subjectId;
    private List<SurmiseLinkDTO> links;

    public KnowledgeSpaceIITARequest() {
    }

    public KnowledgeSpaceIITARequest(int subjectId, List<SurmiseLinkDTO> links) {
        this.subjectId = subjectId;
        this.links = links;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public List<SurmiseLinkDTO> getLinks() {
        return links;
    }

    public void setLinks(List<SurmiseLinkDTO> links) {
        this.links = links;
    }
}
