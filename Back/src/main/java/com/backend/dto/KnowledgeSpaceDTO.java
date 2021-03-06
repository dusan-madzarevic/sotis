package com.backend.dto;

import com.backend.model.Surmise;

import java.util.Set;

public class KnowledgeSpaceDTO {
    private Integer id;
    private String name;
    private Integer subjectId;
    private Set<Surmise> surmises;
    private boolean isReal;

    public KnowledgeSpaceDTO(Integer id, String name, Integer subjectId, Set<Surmise> surmises, boolean isReal) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
        this.surmises = surmises;
        this.isReal = isReal;
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

    public Set<Surmise> getSurmises() {
        return surmises;
    }

    public void setSurmises(Set<Surmise> surmises) {
        this.surmises = surmises;
    }

    public boolean isReal() {
        return isReal;
    }

    public void setReal(boolean real) {
        isReal = real;
    }
}
