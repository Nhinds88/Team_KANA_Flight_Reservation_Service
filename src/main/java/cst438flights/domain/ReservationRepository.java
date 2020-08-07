package cst438flights.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByCustomerid(Integer customerid);
    Reservation findByReservationid(Integer reservationid);
}
