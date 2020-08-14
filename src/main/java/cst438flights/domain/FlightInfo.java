package cst438flights.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class FlightInfo {

    long id;
    String departureAirport;
    String arrivalAirport;
    Timestamp departureDate;
    int firstClassSeats;
    int businessClassSeats;
    int economyClassSeats;
    String status;

    public FlightInfo() {
        this.id = 0;
        this.departureAirport = null;
        this.arrivalAirport = null;
        this.departureDate = null;
        this.firstClassSeats = 0;
        this.businessClassSeats = 0;
        this.economyClassSeats = 0;
        this.status = null;
    }

    public FlightInfo(Flight flight) {
        this.id = flight.getFlightid();
        this.departureAirport = flight.getDepartureairport();
        this.arrivalAirport = flight.getArrivalairport();
        this.departureDate = flight.getDeparturedate();
        this.firstClassSeats = flight.getFirstclass();
        this.businessClassSeats = flight.getBusinessclass();
        this.economyClassSeats = flight.getEconomyclass();
        this.status = flight.getStatus();
    }

    public FlightInfo(long id, String departureAirport, String arrivalAirport, Timestamp departureDate, int firstClassSeats, int businessClassSeats, int economyClassSeats, String status) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.firstClassSeats = firstClassSeats;
        this.businessClassSeats = businessClassSeats;
        this.economyClassSeats = economyClassSeats;
        this.status = status;
    }

    public FlightInfo(long id, String departureAirport, String arrivalAirport, Timestamp departureDate, String status) {
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

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(int firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    public int getBusinessClassSeats() {
        return businessClassSeats;
    }

    public void setBusinessClassSeats(int businessClassSeats) {
        this.businessClassSeats = businessClassSeats;
    }

    public int getEconomyClassSeats() {
        return economyClassSeats;
    }

    public void setEconomyClassSeats(int economyClassSeats) {
        this.economyClassSeats = economyClassSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightInfo that = (FlightInfo) o;
        return id == that.id &&
                firstClassSeats == that.firstClassSeats &&
                businessClassSeats == that.businessClassSeats &&
                economyClassSeats == that.economyClassSeats &&
                Objects.equals(departureAirport, that.departureAirport) &&
                Objects.equals(arrivalAirport, that.arrivalAirport) &&
                Objects.equals(departureDate, that.departureDate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departureAirport, arrivalAirport, departureDate, firstClassSeats, businessClassSeats, economyClassSeats, status);
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "id=" + id +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureDate=" + departureDate +
                ", firstClassSeats=" + firstClassSeats +
                ", businessClassSeats=" + businessClassSeats +
                ", economyClassSeats=" + economyClassSeats +
                ", status='" + status + '\'' +
                '}';
    }
}