package com.example.flightservice.Service;

import com.example.flightservice.Model.Flight;
import com.example.flightservice.Model.Plane;
import com.example.flightservice.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api/FlightService")
public class FlightService {


    @Autowired
    FlightRepository flightRepository;

    @GetMapping(value = "/findAllFlights")
    public List findFlights(){
        List flightList = flightRepository.findAll();

        return (flightList);
    }


    @GetMapping(value = "/findFlight/{id}")
    public Flight findFlight(@PathVariable long id){
        Flight flight = flightRepository.findById(id);

        return (flight);
    }

    @PostMapping(value = "/addNewFlight")
    public Flight newFlight(@RequestBody Flight newFlight){
        RestTemplate restTemplate=new RestTemplate();
        Plane plane=restTemplate.getForObject("http://localhost:8083/api/PlaneService/findPlane/"+newFlight.getPlaneId(), Plane.class);

       // newFlight.set
        System.out.println();

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

    @PostMapping(value = "/getAvailableFlight")
    public List<Flight> getAvailableFlights(@RequestBody Flight flightInfo){

        List<Flight> availableFlights = flightRepository.findByDepartureAndDestinationAndDepartureTimeAndArriveTime(flightInfo.getDeparture(),
                flightInfo.getDestination(),flightInfo.getDepartureTime(), flightInfo.getArriveTime());

        return availableFlights;
    }


}