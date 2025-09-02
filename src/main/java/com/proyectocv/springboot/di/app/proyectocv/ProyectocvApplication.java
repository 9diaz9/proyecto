package com.proyectocv.springboot.di.app.proyectocv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.proyectocv") // Añadir esta línea
@EntityScan("com.proyectocv.model")
@EnableJpaRepositories("com.proyectocv.repository")
public class ProyectocvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProyectocvApplication.class, args);
    }
}