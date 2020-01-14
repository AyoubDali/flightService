package com.example.flightservice.Repository;

import com.example.flightservice.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.List;


@RepositoryRestController
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Flight findById(long id);
    List<Flight> findByDepartureAndDestinationAndDepartureTimeAndArriveTime(String departure, String destination, String departureTime, String arriveTime);
}
