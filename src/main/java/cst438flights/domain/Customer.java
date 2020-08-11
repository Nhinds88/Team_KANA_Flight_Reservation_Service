package cst438flights.domain;

import javax.persistence.*;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customerid")
    private Integer customerid;
    @Column(name="lastname")
    private String lastname;
    @Column(name="firstname")
    private String firstname;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public Customer() { this(0, "Skywalker", "Luke", "jedi@lightside.com", "force"); }

    public Customer(String email) {
        super();
        this.lastname = "";
        this.firstname = "";
        this.email = email;
        this.password = "c5um0n73r3yb4y";
    }

    public Customer(Integer customerid, String lastname, String firstname, String email, String password) {
        super();
        this.customerid = customerid;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomer_ID(Integer customerid) {
        this.customerid = customerid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer [customerid=" + customerid + ", lastName=" + lastname + ", firstName=" + firstname + ", email=" + email + ", password=" + password +"]";
    }
}