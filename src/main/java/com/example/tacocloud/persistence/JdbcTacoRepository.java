package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jdbc.Taco;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;

@Primary
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
        var date = java.sql.Date.valueOf(LocalDate.now());
        long id = jdbcTemplate.update("insert into Taco (name, created_at) values (?, ?)",
            taco.getName(),
            new Date());
        taco.setId(id);
        taco.setCreatedAt(date);
        return taco.getId();
    }

    private void saveIngredient(Taco taco) {

    }
}
