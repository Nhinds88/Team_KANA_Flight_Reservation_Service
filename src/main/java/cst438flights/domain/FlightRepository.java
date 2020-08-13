package cst438flights.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByArrivalairport(String arrivalairport);

    List<Flight> findByDepartureairport(String departureairport);

    @Query("SELECT f FROM Flight f WHERE " +
            "f.arrivalairport = :arrivalAirport AND f.departureairport = :departureAirport")
    List<Flight> findByDepartureArrivalAirport(
            @Param("departureAirport") String departureAirport,
            @Param("arrivalAirport") String arrivalAirport);

    @Query("SELECT f FROM Flight f " +
            "WHERE f.departureairport = :departureAirport AND f.arrivalairport = :arrivalAirport " +
            "AND f.departuredate BETWEEN :startDate AND :endDate ")
    List<Flight> findByDepartureArrivalAirport(
            @Param("departureAirport") String departureAirport,
            @Param("arrivalAirport") String arrivalAirport,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
            );

    Flight findByFlightid(Integer flightid);
}