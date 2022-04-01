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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/design", produces = "application/json")
public class DesignTacoControllerV2 {

    private final JpaTacoRepository jpaTacoRepository;

    @GetMapping(value = "/recent", headers = "version=v2")
    public List<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("createdAt").descending());
        var tacos = jpaTacoRepository.findAll(pageRequest);
        return tacos.getContent();
    }

    @GetMapping(value = "/{id}", headers = {"version=v2"})
    public ResponseEntity<Taco> findById(@PathVariable("id") Long id) {
        var taco = jpaTacoRepository.findById(id);
        if (taco.isPresent()) {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
