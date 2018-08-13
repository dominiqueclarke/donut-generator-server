package com.zcwtc.donutgenerator.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Topping {
    @Id
    @GeneratedValue
    @Column(name="TOPPING_ID")
    private Long id;

    private String name;

    private BigDecimal price;

    public Topping() {

    }

    public Topping(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Topping(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void update(Topping topping) {
        if(topping.getName() != null) {
            this.name = topping.getName();
        }

        if(topping.getPrice() != null) {
            this.price = topping.getPrice();
        }
    }
}
