package com.zcwtc.donutgenerator.Donut;

import com.zcwtc.donutgenerator.domains.Base;
import com.zcwtc.donutgenerator.domains.Donut;
import com.zcwtc.donutgenerator.domains.Topping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class DonutTest {

    private Base base;
    private Topping topping;
    private Set<Topping> toppings;
    private BigDecimal price;

    private Donut donut1;
    private Donut donut2;
    private Donut donut3;

    @Before
    public void setUp() {
        price = new BigDecimal(2.00);
        base = new Base(1L, "vanilla cake", price);
        topping = new Topping(1L, "sprinkles", price);
        toppings = new HashSet<>();
        toppings.add(topping);

        donut1 = new Donut();
        donut2 = new Donut(1L, toppings, base, price);
        donut3 = new Donut(toppings, base, price);
    }

    @Test
    public void instantiation() {
        Assert.assertNotNull(donut1);
        Assert.assertNotNull(donut2);
        Assert.assertNotNull(donut3);
    }

    @Test
    public void getId() {
        Long expected = 1L;
        Long actual = donut2.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setId() {
        donut1.setId(2L);

        Long expected = 2L;
        Long actual = donut1.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getToppings() {
        Assert.assertEquals(toppings, donut2.getToppings());
        Assert.assertEquals(toppings, donut3.getToppings());
    }

    @Test
    public void setToppings() {
        donut1.setToppings(toppings);

        Assert.assertEquals(toppings, donut1.getToppings());

    }

    @Test
    public void getBase() {
        Assert.assertEquals(base, donut2.getBase());
        Assert.assertEquals(base, donut3.getBase());
    }

    @Test
    public void setBase() {
        donut1.setBase(base);

        Assert.assertEquals(base, donut1.getBase());
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(price, donut2.getPrice());
        Assert.assertEquals(price, donut3.getPrice());
    }

    @Test
    public void setPrice() {
        donut1.setPrice(price);

        Assert.assertEquals(price, donut1.getPrice());
    }

    @Test
    public void update() {
        donut1.update(donut2);

        Assert.assertEquals(donut1.getToppings(), donut2.getToppings());
        Assert.assertEquals(donut1.getBase(), donut2.getBase());
        Assert.assertEquals(donut1.getPrice(), donut2.getPrice());
        Assert.assertNotEquals(donut1.getId(), donut2.getId());
    }
}
