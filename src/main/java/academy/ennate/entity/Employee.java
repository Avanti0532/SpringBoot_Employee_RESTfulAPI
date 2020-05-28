package academy.ennate.entity;

import java.util.UUID;
import javax.persistence.*;
@Entity
public class Employee {
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    public Employee(){
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getId(){
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
