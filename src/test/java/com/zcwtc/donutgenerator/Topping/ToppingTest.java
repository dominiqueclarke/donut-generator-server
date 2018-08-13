package com.zcwtc.donutgenerator.Topping;

import com.zcwtc.donutgenerator.domains.Topping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ToppingTest {
    private String name;
    private BigDecimal price;

    private Topping topping1;
    private Topping topping2;
    private Topping topping3;

    @Before
    public void setUp() {
        price = new BigDecimal(2.00);
        name = "vanilla cake";

        topping1 = new Topping();
        topping2 = new Topping(1L, name, price);
        topping3 = new Topping(name, price);
    }

    @Test
    public void instantiation() {
        Assert.assertNotNull(topping1);
        Assert.assertNotNull(topping2);
        Assert.assertNotNull(topping3);
    }

    @Test
    public void getId() {
        Long expected = 1L;
        Long actual = topping2.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setId() {
        topping1.setId(2L);

        Long expected = 2L;
        Long actual = topping1.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getName() {
        Assert.assertEquals(name, topping2.getName());
        Assert.assertEquals(name, topping3.getName());
    }

    @Test
    public void setName() {
        topping1.setName(name);

        Assert.assertEquals(name, topping1.getName());
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(price, topping2.getPrice());
        Assert.assertEquals(price, topping3.getPrice());
    }

    @Test
    public void setPrice() {
        topping1.setPrice(price);

        Assert.assertEquals(price, topping1.getPrice());
    }

    @Test
    public void update() {
        topping1.update(topping2);

        Assert.assertEquals(topping1.getName(), topping2.getName());
        Assert.assertEquals(topping1.getPrice(), topping2.getPrice());
        Assert.assertNotEquals(topping1.getId(), topping2.getId());
    }
}
