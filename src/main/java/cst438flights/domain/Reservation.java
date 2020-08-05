package cst438flights.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @Column(name="reservationid")
    private Integer reservationid;
    @Column(name="customerid")
    private Integer customerid;
    @Column(name="departureflightid")
    private Integer departureflightid;
    @Column(name="seatclass")
    private String seatclass;
    @Column(name="numpassengers")
    private Integer numpassengers;
    @Column(name="priorityboarding")
    private String priorityboarding;
    @Column(name="totalprice")
    private Float totalprice;
    @Column(name="reservationorigin")
    private String reservationorigin;

    public Reservation() {
        this(9999,9999,9999, "first", 1, "no", 300f, "kana");
    }

    public Reservation(Integer reservationid, Integer customerid, Integer departureflightid, String seatclass, Integer numpassengers, String priorityboarding, Float totalprice, String reservationorigin) {
        super();
        this.reservationid = reservationid;
        this.customerid = customerid;
        this.departureflightid = departureflightid;
        this.seatclass = seatclass;
        this.numpassengers = numpassengers;
        this.priorityboarding = priorityboarding;
        this.totalprice = totalprice;
        this.reservationorigin = reservationorigin;
    }

    public Integer getReservationid() {
        return reservationid;
    }

    public void setReservationid(Integer reservation_ID) {
        this.reservationid = reservation_ID;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customer_ID) {
        this.customerid = customer_ID;
    }

    public Integer getDepartureflightid() {
        return departureflightid;
    }

    public void setDepartureflightid(Integer departureFlight_ID) {
        this.departureflightid = departureFlight_ID;
    }

    public String getSeatclass() {
        return seatclass;
    }

    public void setSeatclass(String seatClass) {
        this.seatclass = seatClass;
    }

    public Integer getNumpassengers() {
        return numpassengers;
    }

    public void setNumpassengers(Integer numPassengers) {
        this.numpassengers = numPassengers;
    }

    public String getPriorityboarding() {
        return priorityboarding;
    }

    public void setPriorityboarding(String priorityBoarding) {
        this.priorityboarding = priorityBoarding;
    }

    public Float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Float totalPrice) {
        this.totalprice = totalPrice;
    }

    public String getReservationorigin() {
        return reservationorigin;
    }

    public void setReservationorigin(String reservationOrigin) {
        this.reservationorigin = reservationOrigin;
    }

    @Override
    public String toString() {
        return "Reservation [reservation_ID=" + reservationid + ", customer_ID=" + customerid + ", departureFlight_ID=" + departureflightid + ", seatClass=" + seatclass + ", numPassengers=" + numpassengers + ", priorityBoarding=" + priorityboarding + ", totalPrice=" + totalprice + ", reservationOrigin=" + reservationorigin +"]";
    }
}
