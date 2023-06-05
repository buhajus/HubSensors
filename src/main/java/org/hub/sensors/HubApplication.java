package org.hub.sensors;


import org.hub.sensors.model.GpioPin;
import org.hub.sensors.service.GpioPinService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;


// @SpringBootApplication anotacija yra lygi @Configuration, @EnableAutoConfiguration ir @ComponentScan
// Nurodoma klasėje, turinčioje pagrindinį (main) metodą
@EnableScheduling
@SpringBootApplication
public class HubApplication {

    public static void main(String[] args) {
        // Programos kontrolė deleguojama statiniam klasės SpringApplication metodui run,
        // nurodant aplikacijos šakninį komponentą. Spring karkasas paleis aplikaciją,
        // t.y. startuos serverį su numatytaisiais parametrais.
        SpringApplication.run(HubApplication.class, args);



    }





}