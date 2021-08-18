package com.example.pizzas.controllers;

import com.example.pizzas.repositories.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PizzaController.class)
class PizzaMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaRepository pizzaRepository;


    @Test
    void getAllPizzasReturnsOnePizza() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pizzas"))
                .andExpect(status().is(200));

    }

    @Test
    void getPizza() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pizzas/9"))
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getPizzaQuery() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/pizzas/query?name="))
                .andDo(print())
                .andExpect(status().is(200));
    }


}