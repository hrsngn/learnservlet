package com.mitrais.rms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    private Integer id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="bank_id")
    private Set<Money> money;

    public Bank() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Money> getMoney() {
        return money;
    }

    public void setMoney(Set<Money> money) {
        this.money = money;
    }
}
