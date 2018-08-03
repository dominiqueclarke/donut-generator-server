package com.zcwtc.donutgenerator.controllers;

import com.zcwtc.donutgenerator.domains.Donut;
import com.zcwtc.donutgenerator.repository.DonutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class DonutController {
    private DonutRepository donutRepository;

    @Autowired
    public DonutController(DonutRepository baseRepository) {
        this.donutRepository = baseRepository;
    }

    @RequestMapping(value = "/donuts", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Donut>> index() {
        return new ResponseEntity<>(donutRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/donuts/{donutId}", method = RequestMethod.GET)
    public ResponseEntity<Donut> show(@PathVariable Long donutId) {
        return new ResponseEntity<>(donutRepository.findById(donutId).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/donuts", method = RequestMethod.POST)
    public ResponseEntity<Donut> store(@RequestBody Donut donut) {
        System.out.println(donut.getBase());
        Donut savedDonut = donutRepository.save(donut);
        return new ResponseEntity<>(savedDonut, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/donuts/{donutId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroy(@PathVariable Long donutId) {
        donutRepository.deleteById(donutId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
