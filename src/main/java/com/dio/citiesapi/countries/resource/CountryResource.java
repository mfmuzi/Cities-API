package com.dio.citiesapi.countries.resource;

import com.dio.citiesapi.countries.entity.Country;
import com.dio.citiesapi.countries.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/countries")
public class CountryResource {


    private final CountryRepository repository;

    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Page<Country> countries(Pageable page) {
        return repository.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Country>> getCountryById(@PathVariable Long id){

        if(repository.findById(id).isPresent()){
            return ResponseEntity.ok().body(repository.findById(id));
        }

        return ResponseEntity.notFound().build();
    }

}
