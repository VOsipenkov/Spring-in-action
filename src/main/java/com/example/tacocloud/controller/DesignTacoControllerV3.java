package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Taco;
import com.example.tacocloud.persistence.JpaTacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/design", produces = "application/json")
public class DesignTacoControllerV3 {

    private final JpaTacoRepository jpaTacoRepository;

    @GetMapping(value = "/recent", headers = "version=v3")
    public List<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("createdAt").descending());
        var tacos = jpaTacoRepository.findAll(pageRequest);
        return tacos.getContent();
    }

    @GetMapping(value = "/{id}", headers = {"version=v3"})
    public ResponseEntity<Taco> findById(@PathVariable("id") Long id) {
        var taco = jpaTacoRepository.findById(id);
        if (taco.isPresent()) {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json", headers = {"version=v3"})
    @ResponseStatus(HttpStatus.CREATED)
    public Taco add(@RequestBody Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());
        taco.getIngredients().forEach(i->i.setTacos(List.of(taco)));
        return jpaTacoRepository.save(taco);
    }
}
