package com.example.tacocloud.config;

import com.example.tacocloud.model.jpa.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;

@Configuration
public class HateoasProcessor {

    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> tacoProcessor(EntityLinks links) {
        return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {

            @Override
            public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> model) {
                model.add(Link.of("localhost:8080/api/tacos/recent", "recent"));
                return model;
            }
        };
    }
}
