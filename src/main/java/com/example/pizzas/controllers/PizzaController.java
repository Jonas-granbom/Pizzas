package com.example.pizzas.controllers;

import org.springframework.web.bind.annotation.*;
import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizzas")
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @GetMapping(value ="/pizzas/{id}")
    public Optional<Pizza> getOnePizza(@PathVariable Long id) {
        return pizzaRepository.findById(id);
    }

    @PostMapping("/pizzas")
    public void addPizza(@RequestBody Pizza pizza) {
        pizzaRepository.save(pizza);

    }

    @DeleteMapping("/pizzas/{id}")
    public void deletePizza(@PathVariable Long id) {
        pizzaRepository.deleteById(id);
    }

    @GetMapping("/pizzas/query")
    public List<Pizza> getPizzaByIdUsingQueryParam(@RequestParam String name) {
        return pizzaRepository.findAllByName(name);
    }


}