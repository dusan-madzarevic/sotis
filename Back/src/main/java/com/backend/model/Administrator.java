package com.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends User {
    @OneToMany(mappedBy = "administrator",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<TestType> testTypes;

    public Administrator(){}

    public Set<TestType> getTestTypes() {
        return testTypes;
    }

    public void setTestTypes(Set<TestType> testTypes) {
        this.testTypes = testTypes;
    }
}
