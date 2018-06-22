package com.mitrais.rms.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="money")
public class Money {
    @Id
    private Integer id;
    private Double ammount;

    public Money() {
    }

    public Money(Double ammount) {
        this.ammount = ammount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }
}
