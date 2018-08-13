package com.zcwtc.donutgenerator.Donut;

import com.zcwtc.donutgenerator.controllers.DonutController;
import com.zcwtc.donutgenerator.domains.Base;
import com.zcwtc.donutgenerator.domains.Donut;
import com.zcwtc.donutgenerator.domains.Topping;
import com.zcwtc.donutgenerator.repository.DonutRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.*;

public class DonutControllerTest {

    private DonutRepositoryMock donutRepositoryMock;

    private DonutController donutController;

    private Base base;
    private Set<Topping> toppings;
    private BigDecimal price;

    @Before
    public void setUp() throws Exception {
        List<Donut> donuts = new ArrayList<>();
        toppings = new HashSet<>();
        price = new BigDecimal(2.00);
        base = new Base(1L, "vanilla cake", price);
        Topping topping = new Topping(1L, "sprinkles", price);
        toppings.add(topping);

        donuts.add(new Donut(1L, toppings, base, price));
        donuts.add(new Donut(1L, toppings, base, price));
        donuts.add(new Donut(1L, toppings, base, price));
        this.donutRepositoryMock = new DonutRepositoryMock(donuts);

        this.donutController = new DonutController(this.donutRepositoryMock);
    }

    @Test
    public void index() {
        Assert.assertEquals(donutController.index().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void show() {
        Assert.assertEquals(donutController.show(1L).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void store() {

        Donut donut = new Donut(toppings, base, price);

        Assert.assertEquals(donutController.store(donut).getStatusCode(), HttpStatus.CREATED);

        Assert.assertEquals(this.donutRepositoryMock.donuts.size(), 4);
    }

    @Test
    public void update() {
        int startingSize = this.donutRepositoryMock.donuts.size();
        Topping newTopping = new Topping(2L, "sprinkles", price);
        toppings.add(newTopping);
        Base newBase = new Base(2L, "chocolate cake", price);

        BigDecimal newPrice = new BigDecimal(3.00);

        Donut donut = new Donut(1L, toppings, newBase, newPrice);

        Assert.assertEquals(donutController.update(donut.getId(), donut).getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(this.donutRepositoryMock.donuts.size(), startingSize);
        Assert.assertEquals(toppings, this.donutRepositoryMock.findById(1L).get().getToppings());
        Assert.assertEquals(newBase, this.donutRepositoryMock.findById(1L).get().getBase());
        Assert.assertEquals(newPrice, this.donutRepositoryMock.findById(1L).get().getPrice());

    }

    @Test
    public void destroy() {
        Assert.assertEquals(donutController.destroy(1L).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.donutRepositoryMock.donuts.size(), 2);
    }

    class DonutRepositoryMock implements DonutRepository {

        public List<Donut> donuts;

        public DonutRepositoryMock(List<Donut> donuts) {
            this.donuts = donuts;
        }

        public Long getMaxId() {
            return donuts.get(donuts.size()-1).getId();
        }

        public Integer findIndexOf(Donut donut) {
            for(int i = 0; i < donuts.size(); i++) {
                if (donut.getId() == donuts.get(i).getId()) {
                    return i;
                }
            }
            return -1;
        }


        @Override
        public Donut save(Donut s) {
            Integer index = findIndexOf(s);
            if(index == -1) {
                s.setId(getMaxId()+1);
                donuts.add(s);
            } else {
                donuts.set(index, s);
            }
            return s;
        }

        @Override
        public Iterable<Donut> saveAll(Iterable iterable) {
            return iterable;
        }

        @Override
        public Optional<Donut> findById(Long aLong) {
            for(Donut donut : donuts) {
                if (donut.getId() == aLong) {
                    return Optional.of(donut);
                }
            }
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            for(Donut donut : donuts) {
                if (donut.getId().equals(aLong)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Iterable<Donut> findAll() {
            return donuts;
        }

        @Override
        public Iterable<Donut> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {
            Donut donut = new Donut();
            donut.setId(aLong);

            Integer index = findIndexOf(donut);

            if (index != -1) {
                donuts.remove((int)index);
            }
        }

        @Override
        public void delete(Donut donut) {

        }

        @Override
        public void deleteAll(Iterable<? extends Donut> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    }
}
