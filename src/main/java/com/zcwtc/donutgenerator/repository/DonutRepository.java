package com.zcwtc.donutgenerator.repository;

import com.zcwtc.donutgenerator.domains.Donut;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource
public interface DonutRepository extends CrudRepository<Donut, Long> {

}