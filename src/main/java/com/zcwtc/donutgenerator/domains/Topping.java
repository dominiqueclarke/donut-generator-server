package com.zcwtc.donutgenerator.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Topping {
    @Id
    @GeneratedValue
    @Column(name="TOPPING_ID")
    private Long id;

    private String name;

    public Topping() {

    }

    public Topping(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
