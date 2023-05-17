package org.hub.sensors;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication anotacija yra lygi @Configuration, @EnableAutoConfiguration ir @ComponentScan
// Nurodoma klasėje, turinčioje pagrindinį (main) metodą
@SpringBootApplication
public class HubApplication {
    public static void main(String[] args) {
        // Programos kontrolė deleguojama statiniam klasės SpringApplication metodui run,
        // nurodant aplikacijos šakninį komponentą. Spring karkasas paleis aplikaciją,
        // t.y. startuos serverį su numatytaisiais parametrais.
        SpringApplication.run(HubApplication.class, args);

    }
}