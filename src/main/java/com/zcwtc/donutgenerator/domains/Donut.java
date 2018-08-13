package com.zcwtc.donutgenerator.domains;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Donut {
    @Id
    @GeneratedValue
    @Column(name="DONUT_ID")
    private Long id;

    @ManyToMany
    private Set<Topping> toppings;

    @ManyToOne
    private Base base;

    private BigDecimal price;

    public Donut() {

    }

    public Donut(Long id, Set<Topping> toppings, Base base, BigDecimal price) {
        this.id = id;
        this.toppings = toppings;
        this.base = base;
        this.price = price;
    }

    public Donut(Set<Topping> toppings, Base base, BigDecimal price) {
        this.toppings = toppings;
        this.base = base;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(Set<Topping> toppings) {
        this.toppings = toppings;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void update(Donut donut) {
        if(donut.getToppings() != null) {
            this.toppings = donut.getToppings();
        }

        if(donut.getBase() != null) {
            this.base = donut.getBase();
        }

        if(donut.getPrice() != null) {
            this.price = base.getPrice();
        }
    }
}
