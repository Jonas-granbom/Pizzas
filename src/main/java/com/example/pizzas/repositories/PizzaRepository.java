package com.example.pizzas.repositories;
import com.example.pizzas.entities.Pizza;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findAllByName(String name);


}
