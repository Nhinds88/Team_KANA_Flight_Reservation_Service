package cst438flights.domain;


import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

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
    private Timestamp departuredate;
    @Column(name="firstclass")
    private int firstclass;
    @Column(name="businessclass")
    private int businessclass;
    @Column(name="economyclass")
    private int economyclass;
    @Column(name="status")
    private String status;

    public Flight() {
        this(0, "departureAirport", "arrivalAirport", new Timestamp(2323223232L), 0, 0, 0, "on time");
    }

    public Flight(Integer flightid, String departureairport, String arrivalairport, Timestamp departuredate, int firstclass, int businessclass, int economyclass, String status) {
        this.flightid = flightid;
        this.departureairport = departureairport;
        this.arrivalairport = arrivalairport;
        this.departuredate = departuredate;
        this.firstclass = firstclass;
        this.businessclass = businessclass;
        this.economyclass = economyclass;
        this.status = status;
    }

    public Flight(Integer flightid, String departureairport, String arrivalairport, Timestamp departuredate, String status) {
        super();
        this.flightid = flightid;
        this.departureairport = departureairport;
        this.arrivalairport = arrivalairport;
        this.departuredate = departuredate;
        this.status = status;
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

    public Timestamp getDeparturedate() {
        return departuredate;
    }

    public void setDeparturedate(Timestamp departureDate) {
        this.departuredate = departureDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFirstclass() {
        return firstclass;
    }

    public void setFirstclass(int firstclass) {
        this.firstclass = firstclass;
    }

    public int getBusinessclass() {
        return businessclass;
    }

    public void setBusinessclass(int businessclass) {
        this.businessclass = businessclass;
    }

    public int getEconomyclass() {
        return economyclass;
    }

    public void setEconomyclass(int economyclass) {
        this.economyclass = economyclass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return firstclass == flight.firstclass &&
                businessclass == flight.businessclass &&
                economyclass == flight.economyclass &&
                Objects.equals(flightid, flight.flightid) &&
                Objects.equals(departureairport, flight.departureairport) &&
                Objects.equals(arrivalairport, flight.arrivalairport) &&
                Objects.equals(departuredate, flight.departuredate) &&
                Objects.equals(status, flight.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightid, departureairport, arrivalairport, departuredate, firstclass, businessclass, economyclass, status);
    }

    @Override
    public String toString() {
        return "Flight [flight_ID=" + flightid + ", departureAirport=" + departureairport + ", arrivalAirport=" + arrivalairport + ", departureDate=" + departuredate + ", status=" + status + "]";
    }
}
