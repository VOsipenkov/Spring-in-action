package com.example.tacocloud.persistence;

import com.example.tacocloud.model.jpa.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTacoRepository extends JpaRepository<Taco, Long> {
}
