package com.example.flightservice;

import com.example.flightservice.Model.Flight;
import com.example.flightservice.Repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class FlightserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightserviceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(FlightRepository flightRepository){
        return args->{
            Stream.of("Paris","Belgique","Germany").forEach(dp->{
                flightRepository.save(new Flight(0,dp,20));
            });

            flightRepository.findAll().forEach(System.out::println);

        };
    }
}
