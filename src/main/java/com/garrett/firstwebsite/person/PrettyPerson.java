package com.garrett.firstwebsite.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PrettyPerson {
    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private String firstName;
    private String lastName;
    private String profession;

    public PrettyPerson() {

    }

    public PrettyPerson(long id,
                  long user_id,
                  String firstName,
                  String lastName,
                  String profession) {
        super();
        this.id = id;
        this.userId = user_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
