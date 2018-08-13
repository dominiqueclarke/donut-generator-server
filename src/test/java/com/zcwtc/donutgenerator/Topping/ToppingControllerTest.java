package com.zcwtc.donutgenerator.Topping;

import com.zcwtc.donutgenerator.controllers.ToppingController;
import com.zcwtc.donutgenerator.domains.Topping;
import com.zcwtc.donutgenerator.repository.ToppingRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToppingControllerTest {

    private ToppingRepositoryMock toppingRepositoryMock;

    private ToppingController toppingController;

    private BigDecimal price;

    @Before
    public void setUp() throws Exception {
        List<Topping> toppings = new ArrayList<>();

        price = new BigDecimal(2.00);

        toppings.add(new Topping(1L, "sprinkles", price));
        toppings.add(new Topping(2L, "oreos", price));
        toppings.add(new Topping(3L, "bacon", price));
        this.toppingRepositoryMock = new ToppingRepositoryMock(toppings);

        this.toppingController = new ToppingController(this.toppingRepositoryMock);

    }

    @Test
    public void index() {
        Assert.assertEquals(toppingController.index().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void show() {
        Assert.assertEquals(toppingController.show(1L).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void store() {

        Topping topping = new Topping("coconut", price);

        Assert.assertEquals(toppingController.store(topping).getStatusCode(), HttpStatus.CREATED);

        Assert.assertEquals(this.toppingRepositoryMock.toppings.size(), 4);
    }

    @Test
    public void update() {
        int startingSize = this.toppingRepositoryMock.toppings.size();
        BigDecimal newPrice = new BigDecimal(3.00);
        String newName = "chocolate sprinkles";

        Topping topping = new Topping(1L, newName, newPrice);

        Assert.assertEquals(toppingController.update(topping.getId(), topping).getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(this.toppingRepositoryMock.toppings.size(), startingSize);
        Assert.assertEquals(newName, this.toppingRepositoryMock.findById(1L).get().getName());
        Assert.assertEquals(newPrice, this.toppingRepositoryMock.findById(1L).get().getPrice());
    }

    @Test
    public void destroy() {
        Assert.assertEquals(toppingController.destroy(1L).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.toppingRepositoryMock.toppings.size(), 2);
    }

    class ToppingRepositoryMock implements ToppingRepository {

        public List<Topping> toppings;

        public ToppingRepositoryMock(List<Topping> toppings) {
            this.toppings = toppings;
        }

        public Long getMaxId() {
            return toppings.get(toppings.size()-1).getId();
        }

        public Integer findIndexOf(Topping topping) {
            for(int i = 0; i < toppings.size(); i++) {
                if (topping.getId() == toppings.get(i).getId()) {
                    return i;
                }
            }
            return -1;
        }


        @Override
        public Topping save(Topping s) {
            Integer index = findIndexOf(s);
            if(index == -1) {
                s.setId(getMaxId()+1);
                toppings.add(s);
            } else {
                toppings.set(index, s);
            }
            return s;
        }

        @Override
        public Iterable<Topping> saveAll(Iterable iterable) {
            return iterable;
        }

        @Override
        public Optional<Topping> findById(Long aLong) {
            for(Topping topping : toppings) {
                if (topping.getId() == aLong) {
                    return Optional.of(topping);
                }
            }
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            for(Topping topping : toppings) {
                if (topping.getId().equals(aLong)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Iterable<Topping> findAll() {
            return toppings;
        }

        @Override
        public Iterable<Topping> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {
            Topping topping = new Topping();
            topping.setId(aLong);

            Integer index = findIndexOf(topping);

            if (index != -1) {
                toppings.remove((int)index);
            }
        }

        @Override
        public void delete(Topping topping) {

        }

        @Override
        public void deleteAll(Iterable<? extends Topping> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    }
}
