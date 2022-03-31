package com.example.tacocloud.controller;

import com.example.tacocloud.model.jpa.Taco;
import com.example.tacocloud.persistence.JpaTacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/design", produces = "application/json")
public class DesignTacoControllerV2 {

    private final JpaTacoRepository jpaTacoRepository;

    @GetMapping(value = "/recent")
    public List<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("createdAt").descending());
        var tacos = jpaTacoRepository.findAll(pageRequest);
        return tacos.getContent();
    }
}
