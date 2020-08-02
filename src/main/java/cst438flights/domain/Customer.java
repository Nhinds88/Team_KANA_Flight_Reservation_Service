package cst438flights.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    private long customer_ID;
    private String lastName;
    private String firstName;
    private String email;
    private String password;

    public Customer() { this(0, "Skywalker", "Luke", "jedi@lightside.com", "force"); }


    public Customer(long customer_ID, String lastname, String firstName, String email, String password) {
        super();
        this.customer_ID = customer_ID;
        this.lastName = lastname;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }

    public long getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(long customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
        return "Customer [customer_ID=" + customer_ID + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email + ", password=" + password +"]";
    }
}