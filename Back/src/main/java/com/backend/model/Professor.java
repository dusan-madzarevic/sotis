package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Professor")
public class Professor extends User {
    @OneToMany(mappedBy = "professorId",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TestType> testTypes;

    public Professor(){}

    public Set<TestType> getTestTypes() {
        return testTypes;
    }

    public void setTestTypes(Set<TestType> testTypes) {
        this.testTypes = testTypes;
    }
}
