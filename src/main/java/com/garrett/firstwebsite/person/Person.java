package com.garrett.firstwebsite.person;

import com.garrett.firstwebsite.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private long id;
    private long userId;
    private int professionId;

    public Person() {

    }

    public Person(long id,
                long user_id,
                int professionId) {
        super();
        this.id = id;
        this.userId = user_id;
        this.professionId = professionId;
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

    public int getProfessionId() {
        return professionId;
    }

    public void setProfessionId(int professionId) {
        this.professionId = professionId;
    }
}
