package com.zcwtc.donutgenerator.controllers;

import com.zcwtc.donutgenerator.domains.Topping;
import com.zcwtc.donutgenerator.repository.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class ToppingController {
    private ToppingRepository toppingRepository;

    @Autowired
    public ToppingController(ToppingRepository toppingRepository) {
        this.toppingRepository = toppingRepository;
    }

    @RequestMapping(value = "/toppings", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Topping>> index() {
        return new ResponseEntity<>(toppingRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/topping/{toppingId}", method = RequestMethod.GET)
    public ResponseEntity<Topping> show(@PathVariable Long toppingId) {
        return new ResponseEntity<>(toppingRepository.findById(toppingId).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/toppings", method = RequestMethod.POST)
    public ResponseEntity<Topping> store(@RequestBody Topping topping) {
        Topping savedTopping = toppingRepository.save(topping);
        return new ResponseEntity<>(savedTopping, HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/toppings/{toppingId}", method = RequestMethod.PUT)
//    public ResponseEntity<Topping> update(@PathVariable Long toppingId, @RequestBody Topping topping) {
//        Topping foundTopping = toppingRepository.findById(toppingId).get();
//
//        topping.update(topping);
//
//        Topping savedDog = toppingRepository.save(foundTopping);
//
//        return new ResponseEntity<>(savedDog, HttpStatus.OK);
//    }

    @RequestMapping(value = "/toppings/{toppingId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroy(@PathVariable Long toppingId) {
        toppingRepository.deleteById(toppingId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    @RequestMapping(value = "/dogs/{dogId}/walkers", method = RequestMethod.GET)
//    public ResponseEntity<Walker> getWalker(@PathVariable Long dogId) {
//        Long walkerId = dogRepository.getWalkerIdByDogId(dogId);
//        Walker walker = walkerRepository.findById(walkerId).get();
//
//        return new ResponseEntity<>(walker, HttpStatus.OK);
//    }
}
