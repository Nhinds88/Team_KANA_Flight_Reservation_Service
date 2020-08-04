package cst438flights.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.sql.Date;

@Entity
@Table(name="flight")
public class Flight {

    @Id
    private long flightID;
    private String departureAirport;
    private String arrivalAirport;
    private Date departureDate;

    public Flight() {
        this(0, "departureAirport", "arrivalAirport", new Date(2323223232L));
    }

    public Flight(long flightID, String departureAirport, String arrivalAirport, Date departureDate) {
        super();
        this.flightID = flightID;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
    }

    public long getFlightID() {
        return flightID;
    }

    public void setFlightID(long flight_ID) {
        this.flightID = flight_ID;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Flight [flight_ID=" + flightID + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", departureDate=" + departureDate + "]";
    }
}
