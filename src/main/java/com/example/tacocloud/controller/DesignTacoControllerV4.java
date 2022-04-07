package com.example.tacocloud.controller;

import com.example.tacocloud.model.hateoas.TacoModel;
import com.example.tacocloud.model.hateoas.TacoModelAssembler;
import com.example.tacocloud.persistence.JpaTacoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@RepositoryRestController
public class DesignTacoControllerV4 {

    private final JpaTacoRepository jpaTacoRepository;

    @GetMapping(headers = {"version=v4"}, path = "/tacos/recent")
    public ResponseEntity<CollectionModel<TacoModel>> get() {
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("createdAt").descending());
        var tacos = jpaTacoRepository.findAll(pageRequest).getContent();
        var tacoModels = new TacoModelAssembler(DesignTacoControllerV4.class, TacoModel.class)
            .toCollectionModel(tacos);
        var link = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(DesignTacoControllerV4.class).get()).withRel("recent");
        tacoModels.add(link);
        return new ResponseEntity<>(tacoModels, HttpStatus.OK);
    }
}
