package cst438flights.domain;

import java.sql.Date;

public class FlightInfo {

    private long id;
    private String departureAirport;
    private String arrivalAirport;
    private Date departureDate;
    private String status;

    public FlightInfo() {
        this.id = 0;
        this.departureAirport = null;
        this.arrivalAirport = null;
        this.departureDate = null;
        this.status = null;
    }

    public FlightInfo(Flight flight) {
        this.id = flight.getFlightid();
        this.departureAirport = flight.getDepartureairport();
        this.arrivalAirport = flight.getArrivalairport();
        this.departureDate = flight.getDeparturedate();
        this.status = flight.getStatus();
    }

    public FlightInfo(long id, String departureAirport, String arrivalAirport, Date departureDate, String status) {
        super();
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Flight [id=" + id + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", departureDate=" + departureDate + ", status=" + status + "]";
    }
}