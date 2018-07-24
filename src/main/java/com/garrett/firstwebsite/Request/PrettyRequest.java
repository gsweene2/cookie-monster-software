package com.garrett.firstwebsite.Request;

import org.apache.tomcat.jni.Local;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class PrettyRequest {
    @Id
    @GeneratedValue
    private long id;
    private String itemName;
    private double itemCost;
    private String requestorFirstName;
    private String requestorLastName;
    private String dateRequested;
    @Nullable
    private String dateFilled;
    private String filledByFirstName;
    private String filledByLastName;
    private String filledStatus;

    public PrettyRequest() {

    }

    public PrettyRequest(long id,
                   String itemName,
                   long userId,
                   double itemCost,
                   String requestorFirstName,
                   String requestorLastName,
                         String dateRequested,
                         String dateFilled,
                         String filledByFirstName,
                         String filledByLastName,
                         String filledStatus) {
        super();
        this.id = id;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.requestorFirstName = requestorFirstName;
        this.requestorLastName = requestorLastName;
        this.dateRequested = dateRequested;
        this.dateFilled = dateFilled;
        this.filledByFirstName = filledByFirstName;
        this.filledByLastName = filledByLastName;
        this.filledStatus = filledStatus;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public String getRequestorFirstName() {
        return requestorFirstName;
    }

    public void setRequestorFirstName(String requestorFirstName) {
        this.requestorFirstName = requestorFirstName;
    }

    public String getRequestorLastName() {
        return requestorLastName;
    }

    public void setRequestorLastName(String requestorLastName) {
        this.requestorLastName = requestorLastName;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getDateFilled() {
        return dateFilled;
    }

    public void setDateFilled(String dateFilled) {
        this.dateFilled = dateFilled;
    }

    public String getFilledByFirstName() {
        return filledByFirstName;
    }

    public void setFilledByFirstName(String filledByFirstName) {
        this.filledByFirstName = filledByFirstName;
    }

    public String getFilledByLastName() {
        return filledByLastName;
    }

    public void setFilledByLastName(String filledByLastName) {
        this.filledByLastName = filledByLastName;
    }

    public String getFilledStatus() {
        return filledStatus;
    }

    public void setFilledStatus(String filledStatus) {
        this.filledStatus = filledStatus;
    }
}
