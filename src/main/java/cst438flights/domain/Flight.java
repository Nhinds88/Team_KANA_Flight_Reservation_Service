package cst438flights.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="flight")
public class Flight {

    @Id
    private long flight_ID;
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;

    public Flight() {
        this(0, "departureAirport", "arrivalAirport", "1/1/20");
    }

    public Flight(long flight_ID, String departureAirport, String arrivalAirport, String departureDate) {
        super();
        this.flight_ID = flight_ID;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
    }

    public long getFlight_ID() {
        return flight_ID;
    }

    public void setFlight_ID(long flight_ID) {
        this.flight_ID = flight_ID;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Flight [flight_ID=" + flight_ID + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", departureDate=" + departureDate + "]";
    }
}
