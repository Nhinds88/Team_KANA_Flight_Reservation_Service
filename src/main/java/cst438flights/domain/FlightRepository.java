package cst438flights.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByArrivalairport(String arrivalairport);

    @Query("SELECT f FROM Flight f WHERE " +
            "f.arrivalairport = :arrivalAirport AND f.departureairport = :departureAirport")
    List<Flight> findByDepartureArrivalAirport(
            @Param("departureAirport") String departureAirport,
            @Param("arrivalAirport") String arrivalAirport);

    Flight findByFlightid(Integer flightid);
}