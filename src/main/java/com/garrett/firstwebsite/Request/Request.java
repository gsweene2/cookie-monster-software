package com.garrett.firstwebsite.Request;

import org.apache.tomcat.jni.Local;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private long id;
    private long itemId;
    private long userId;
    private int filled;
    @Nullable
    private long fillerId;
    private LocalDate dateRequested;
    @Nullable
    private LocalDate dateFilled;

    public Request() {

    }

    public Request(long id,
                long itemId,
                long userId,
                int filled,
                long fillerId,
                LocalDate dateRequested,
                   LocalDate dateFilled) {
        super();
        this.id = id;
        this.itemId = itemId;
        this.userId = userId;
        this.filled = filled;
        this.fillerId = fillerId;
        this.dateRequested = dateRequested;
        if (dateFilled == null){
            this.dateFilled = null;
        } else {
            this.dateFilled = dateFilled;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getFilled() {
        return filled;
    }

    public void setFilled(int filled) {
        this.filled = filled;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDate dateRequested) {
        this.dateRequested = dateRequested;
    }

    public LocalDate getDateFilled() {
        return dateFilled;
    }

    public void setDateFilled(LocalDate dateFilled) {
        this.dateFilled = dateFilled;
    }

    public long getFillerId() {
        return fillerId;
    }

    public void setFillerId(long fillerId) {
        this.fillerId = fillerId;
    }
}
