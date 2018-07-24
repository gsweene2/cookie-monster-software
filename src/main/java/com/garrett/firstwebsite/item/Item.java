package com.garrett.firstwebsite.item;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private double amount;
    private String instock;

    public Item() {

    }

    public Item(long id,
                String name,
                double amount,
                String instock) {
        super();
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.instock = instock;
    }

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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getInstock() {
        return instock;
    }

    public void setInstock(String instock) {
        this.instock = instock;
    }
}
