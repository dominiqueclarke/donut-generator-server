package com.zcwtc.donutgenerator.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Base {
    @Id
    @GeneratedValue
    @Column(name="BASE_ID")
    private Long id;

    private String name;

    private BigDecimal price;

    public Base() {

    }

    public Base(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Base(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void update(Base base) {
        if(base.getName() != null) {
            this.name = base.getName();
        }

        if(base.getPrice() != null) {
            this.price = base.getPrice();
        }
    }
}
