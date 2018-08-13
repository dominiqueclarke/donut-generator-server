package com.zcwtc.donutgenerator.Base;

import com.zcwtc.donutgenerator.controllers.BaseController;
import com.zcwtc.donutgenerator.domains.Base;
import com.zcwtc.donutgenerator.repository.BaseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseControllerTest {

    private BaseRepositoryMock baseRepositoryMock;

    private BaseController baseController;

    private BigDecimal price;

    @Before
    public void setUp() throws Exception {
        List<Base> bases = new ArrayList<>();

        price = new BigDecimal(2.00);

        bases.add(new Base(1L, "vanilla cake", price));
        bases.add(new Base(2L, "chocolate cake", price));
        bases.add(new Base(3L, "strawberry cake", price));
        this.baseRepositoryMock = new BaseRepositoryMock(bases);

        this.baseController = new BaseController(this.baseRepositoryMock);

    }

    @Test
    public void index() {
        Assert.assertEquals(baseController.index().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void show() {
        Assert.assertEquals(baseController.show(1L).getStatusCode(), HttpStatus.OK);
    }


    @Test
    public void store() {

        Base base = new Base("vanilla yeast", price);

        Assert.assertEquals(baseController.store(base).getStatusCode(), HttpStatus.CREATED);

        Assert.assertEquals(this.baseRepositoryMock.bases.size(), 4);
    }

    @Test
    public void update() {
        int startingSize = this.baseRepositoryMock.bases.size();
        BigDecimal newPrice = new BigDecimal(3.00);
        String newName = "coconut cake";

        Base base = new Base(1L, newName, newPrice);

        Assert.assertEquals(baseController.update(base.getId(), base).getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(this.baseRepositoryMock.bases.size(), startingSize);
        Assert.assertEquals(newName, this.baseRepositoryMock.findById(1L).get().getName());
        Assert.assertEquals(newPrice, this.baseRepositoryMock.findById(1L).get().getPrice());
    }

    @Test
    public void destroy() {
        Assert.assertEquals(baseController.destroy(1L).getStatusCode(), HttpStatus.OK);

        Assert.assertEquals(this.baseRepositoryMock.bases.size(), 2);
    }

    class BaseRepositoryMock implements BaseRepository {

        public List<Base> bases;

        public BaseRepositoryMock(List<Base> bases) {
            this.bases = bases;
        }

        public Long getMaxId() {
            return bases.get(bases.size()-1).getId();
        }

        public Integer findIndexOf(Base base) {
            for(int i = 0; i < bases.size(); i++) {
                if (base.getId() == bases.get(i).getId()) {
                    return i;
                }
            }
            return -1;
        }


        @Override
        public Base save(Base s) {
            Integer index = findIndexOf(s);
            if(index == -1) {
                s.setId(getMaxId()+1);
                bases.add(s);
            } else {
                bases.set(index, s);
            }
            return s;
        }

        @Override
        public Iterable<Base> saveAll(Iterable iterable) {
            return iterable;
        }

        @Override
        public Optional<Base> findById(Long aLong) {
            for(Base base : bases) {
                if (base.getId() == aLong) {
                    return Optional.of(base);
                }
            }
            return Optional.empty();
        }

        @Override
        public boolean existsById(Long aLong) {
            for(Base base : bases) {
                if (base.getId().equals(aLong)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Iterable<Base> findAll() {
            return bases;
        }

        @Override
        public Iterable<Base> findAllById(Iterable<Long> iterable) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {
            Base base = new Base();
            base.setId(aLong);

            Integer index = findIndexOf(base);

            if (index != -1) {
                bases.remove((int)index);
            }
        }

        @Override
        public void delete(Base base) {

        }

        @Override
        public void deleteAll(Iterable<? extends Base> iterable) {

        }

        @Override
        public void deleteAll() {

        }
    }
}
