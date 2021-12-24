package com.backend.dto;

import com.backend.model.Surmise;

import java.util.Set;

public class KnowledgeSpaceGraphDTO {

    private Integer id;
    private String name;
    private Integer subjectId;
    private Set<SurmiseGraphDTO> surmises;
    private boolean realSpace;

    public KnowledgeSpaceGraphDTO() {
    }

    public KnowledgeSpaceGraphDTO(Integer id, String name, Integer subjectId, Set<SurmiseGraphDTO> surmises, boolean realSpace) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.surmises = surmises;
        this.realSpace = realSpace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Set<SurmiseGraphDTO> getSurmises() {
        return surmises;
    }

    public void setSurmises(Set<SurmiseGraphDTO> surmises) {
        this.surmises = surmises;
    }

    public boolean isRealSpace() {
        return realSpace;
    }

    public void setRealSpace(boolean realSpace) {
        this.realSpace = realSpace;
    }
}
