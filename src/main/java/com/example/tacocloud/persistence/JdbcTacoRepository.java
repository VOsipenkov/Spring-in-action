package com.example.tacocloud.persistence;

import com.example.tacocloud.model.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTaco(taco);
        taco.getIngredients().parallelStream().forEach(i ->
            jdbcTemplate.update("insert into Taco_Ingredients (taco, ingredient) values(?, ?)",
                tacoId, i));
        return taco;
    }

    private long saveTaco(Taco taco) {
        return jdbcTemplate.update("insert into Taco (name, createdAt) values (?, ?)",
            taco.getName(),
            new Date());
    }

    private void saveIngredient(Taco taco) {

    }
}
