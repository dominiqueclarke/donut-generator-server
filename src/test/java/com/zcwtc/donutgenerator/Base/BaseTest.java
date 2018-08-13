package com.zcwtc.donutgenerator.Base;

import com.zcwtc.donutgenerator.domains.Base;
import com.zcwtc.donutgenerator.domains.Donut;
import com.zcwtc.donutgenerator.domains.Topping;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class BaseTest {
    private String name;
    private BigDecimal price;

    private Base base1;
    private Base base2;
    private Base base3;

    @Before
    public void setUp() {
        price = new BigDecimal(2.00);
        name = "vanilla cake";

        base1 = new Base();
        base2 = new Base(1L, name, price);
        base3 = new Base(name, price);
    }

    @Test
    public void instantiation() {
        Assert.assertNotNull(base1);
        Assert.assertNotNull(base2);
        Assert.assertNotNull(base3);
    }

    @Test
    public void getId() {
        Long expected = 1L;
        Long actual = base2.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setId() {
        base1.setId(2L);

        Long expected = 2L;
        Long actual = base1.getId();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getName() {
        Assert.assertEquals(name, base2.getName());
        Assert.assertEquals(name, base3.getName());
    }

    @Test
    public void setName() {
        base1.setName(name);

        Assert.assertEquals(name, base1.getName());
    }

    @Test
    public void getPrice() {
        Assert.assertEquals(price, base2.getPrice());
        Assert.assertEquals(price, base3.getPrice());
    }

    @Test
    public void setPrice() {
        base1.setPrice(price);

        Assert.assertEquals(price, base1.getPrice());
    }

    @Test
    public void update() {
        base1.update(base2);

        Assert.assertEquals(base1.getName(), base2.getName());
        Assert.assertEquals(base1.getPrice(), base2.getPrice());
        Assert.assertNotEquals(base1.getId(), base2.getId());
    }
}
