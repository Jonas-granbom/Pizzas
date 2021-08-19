package com.example.pizzas.controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/pizzas/{id}")
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

    @PutMapping("/pizzas/{id}")
    public void putPizza(
            @PathVariable Long id,
            @RequestBody Pizza pizza) {
        Pizza pizzaboiii = new Pizza(id, pizza.getName(), pizza.getPrice(), pizza.getIngredients());
        pizzaRepository.save(pizzaboiii);

    }

    @PatchMapping("/pizzas/{id}")
    public ResponseEntity<Pizza> patchPizza(@PathVariable(value = "id") Long id, @RequestBody Pizza pizza) {

        Pizza oldPizza = pizzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pizza not found" + id));
        Pizza newPizza = new Pizza();

        if(oldPizza.getName().equals(pizza.getName())|| pizza.getName() == null ){
            newPizza.setName(oldPizza.getName());
        }else{
            newPizza.setName(pizza.getName());
        }
        if(oldPizza.getIngredients().equals(pizza.getIngredients())|| pizza.getIngredients() == null ){
            newPizza.setIngredients(oldPizza.getIngredients());
        }else{
            newPizza.setIngredients(pizza.getIngredients());
        }
        if(oldPizza.getPrice() == (pizza.getPrice())|| pizza.getPrice() == 0 ){
            newPizza.setPrice(oldPizza.getPrice());
        }else{
            newPizza.setPrice(pizza.getPrice());
        }

        newPizza.setId(id);

        pizzaRepository.save(newPizza);

        return ResponseEntity.ok(newPizza);

    }


}