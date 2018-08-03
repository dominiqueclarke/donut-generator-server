package com.zcwtc.donutgenerator.controllers;

import com.zcwtc.donutgenerator.domains.Base;
import com.zcwtc.donutgenerator.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller
public class BaseController {
    private BaseRepository baseRepository;

    @Autowired
    public BaseController(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @RequestMapping(value = "/bases", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Base>> index() {
        return new ResponseEntity<>(baseRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/base/{baseId}", method = RequestMethod.GET)
    public ResponseEntity<Base> show(@PathVariable Long baseId) {
        return new ResponseEntity<>(baseRepository.findById(baseId).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bases", method = RequestMethod.POST)
    public ResponseEntity<Base> store(@RequestBody Base base) {
        Base savedBase = baseRepository.save(base);
        return new ResponseEntity<>(savedBase, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bases/{baseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> destroy(@PathVariable Long baseId) {
        baseRepository.deleteById(baseId);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
