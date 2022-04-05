package com.example.tacocloud.controller;

import com.example.tacocloud.model.hateoas.TacoModel;
import com.example.tacocloud.model.hateoas.TacoModelAssembler;
import com.example.tacocloud.model.jpa.Taco;
import com.example.tacocloud.persistence.JpaTacoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/design", produces = "application/json")
public class DesignTacoControllerV3 {

    private final JpaTacoRepository jpaTacoRepository;

    @GetMapping(value = "/recent", headers = "version=v3")
    public CollectionModel<Taco> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("createdAt").descending());
        var tacos = jpaTacoRepository.findAll(pageRequest).getContent();
        var tacoCollectionModel = CollectionModel.of(tacos);

        var selfLink = linkTo(DesignTacoControllerV3.class).slash("/recent").withRel("recents");
        tacoCollectionModel.add(selfLink);
        return tacoCollectionModel;
    }

    @GetMapping(value = "/{id}", headers = {"version=v3"})
    public ResponseEntity<TacoModel> findById(@PathVariable("id") Long id) {
        var taco = jpaTacoRepository.findById(id);
        if (taco.isPresent()) {
            var tacoModel = new TacoModelAssembler(DesignTacoControllerV3.class, TacoModel.class).toModel(taco.get());
            return new ResponseEntity<>(tacoModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json", headers = {"version=v3"})
    @ResponseStatus(HttpStatus.CREATED)
    public Taco add(@RequestBody Taco taco) {
        taco.setCreatedAt(LocalDateTime.now());
        taco.getIngredients().forEach(i -> i.setTacos(List.of(taco)));
        return jpaTacoRepository.save(taco);
    }

    @PutMapping(consumes = "application/json", headers = {"version=v3"})
    public Taco put(@RequestBody Taco taco) {
        return jpaTacoRepository.save(taco);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}", headers = {"version=v3"})
    public void delete(@PathVariable long id) {
        jpaTacoRepository.deleteById(id);
    }
}
