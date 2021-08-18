package com.example.pizzas;
import com.example.pizzas.entities.Pizza;
import com.example.pizzas.repositories.PizzaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class PizzasApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzasApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(PizzaRepository pizzaRepository){
        return (args) -> {

            if(pizzaRepository.count() == 0){
                pizzaRepository.save(new Pizza(1L, "Hawaii",80,"Ost, tomatsås, skinka, ananas"));

            }


        };

    }
}
