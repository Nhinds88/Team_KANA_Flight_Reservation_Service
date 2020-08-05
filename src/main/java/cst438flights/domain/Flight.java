package cst438flights.domain;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="flight")
public class Flight {

    @Id
    @Column(name="flightid")
    private Integer flightid;
    @Column(name="departureairport")
    private String departureairport;
    @Column(name="arrivalairport")
    private String arrivalairport;
    @Column(name="departuredate")
    private Date departuredate;

    public Flight() {
        this(0, "departureAirport", "arrivalAirport", new Date(2323223232L));
    }

    public Flight(Integer flightid, String departureairport, String arrivalairport, Date departuredate) {
        super();
        this.flightid = flightid;
        this.departureairport = departureairport;
        this.arrivalairport = arrivalairport;
        this.departuredate = departuredate;
    }

    public Integer getFlightid() {
        return flightid;
    }

    public void setFlightid(Integer flight_ID) {
        this.flightid = flight_ID;
    }

    public String getDepartureairport() {
        return departureairport;
    }

    public void setDepartureairport(String departureAirport) {
        this.departureairport = departureAirport;
    }

    public String getArrivalairport() {
        return arrivalairport;
    }

    public void setArrivalairport(String arrivalAirport) {
        this.arrivalairport = arrivalAirport;
    }

    public Date getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Date departureDate) {
        this.departuredate = departureDate;
    }

    @Override
    public String toString() {
        return "Flight [flight_ID=" + flightid + ", departureAirport=" + departureairport + ", arrivalAirport=" + arrivalairport + ", departureDate=" + departuredate + "]";
    }
}
