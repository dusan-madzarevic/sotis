package com.backend.dto;

public class KnowledgeStateDTO {
    private Integer id;
    private String name;
    private Integer prednodni;
    private Integer sledeci;

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

    public Integer getPrednodni() {
        return prednodni;
    }

    public void setPrednodni(Integer prednodni) {
        this.prednodni = prednodni;
    }

    public Integer getSledeci() {
        return sledeci;
    }

    public void setSledeci(Integer sledeci) {
        this.sledeci = sledeci;
    }
}
