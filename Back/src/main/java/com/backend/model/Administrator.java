package com.backend.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Administrator")
public class Administrator extends User {
    public Administrator(){}
}
