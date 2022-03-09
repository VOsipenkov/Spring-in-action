package com.example.tacocloud.persistence;

import com.example.tacocloud.model.Taco;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository {
    Taco save(Taco taco);
}
