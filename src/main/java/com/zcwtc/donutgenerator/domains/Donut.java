package com.zcwtc.donutgenerator.domains;

import javax.persistence.*;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
