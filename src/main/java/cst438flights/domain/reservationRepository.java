package cst438flights.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface reservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomer_ID(Long customer_ID);
}
