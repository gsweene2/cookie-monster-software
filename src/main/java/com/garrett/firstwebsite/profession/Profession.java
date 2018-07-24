package com.garrett.firstwebsite.profession;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profession {
    @Id
    @GeneratedValue
    private long id;
    public String name;

    public Profession(long id, String name){
        this.id = id;
        this.name = name;
    }

    public Profession(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
