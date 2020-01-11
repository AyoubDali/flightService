package com.example.flightservice.Service;

import com.example.flightservice.Model.Flight;
import com.example.flightservice.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/Service")
public class FlightService {


    @Autowired
    FlightRepository flightRepository;

    @GetMapping(value = "/findAllFlights")
    public List findFlights(){
        List flightList = flightRepository.findAll();

        return (flightList);
    }

    @PostMapping(value = "/saveFlight")
    public Flight newFlight(@RequestBody Flight newFlight){
        flightRepository.save(newFlight);
        return newFlight;
    }

    @DeleteMapping(value = "/deleteFlight/{id}")
    public String deleteFlight(@PathVariable Long id){

        flightRepository.deleteById(id);
        return "OK!" ;
    }

    @PutMapping(value = "/editFlight/{id}")
    public Flight editFlight(@RequestBody Flight fightInfo, @PathVariable long id){

        Flight flight = flightRepository.findById(id);
        flight.setDepartureTime(fightInfo.getDepartureTime());
        flightRepository.save(flight);
        return flight ;
    }
}