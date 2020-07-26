package cst438flights.domain;

public class FlightInfo {

    long id;
    String departureAirport;
    String arrivalAirport;
    String departureDate;

    public FlightInfo() {
        this.id = 0;
        this.departureAirport = null;
        this.arrivalAirport = null;
        this.departureDate = null;
    }

    public FlightInfo(Flight flight) {
        this.id = flight.getFlight_ID();
        this.departureAirport = flight.getDepartureAirport();
        this.arrivalAirport = flight.getArrivalAirport();
        this.departureDate = flight.getDepartureDate();
    }

    public FlightInfo(long id, String departureAirport, String arrivalAirport, String departureDate) {
        super();
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
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

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public String toString() {
        return "Flight [id=" + id + ", departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport + ", departureDate=" + departureDate + "]";
    }
}