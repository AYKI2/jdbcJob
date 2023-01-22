package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private byte age;
    private String email;
    private int jobId;//references

    public Employee(String firstName, String lastName, byte age, String email, int jobId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.jobId = jobId;
    }

    @Override
    public String toString() {
        return "\nEmployee:" +
                "\nid = " + id +
                "\nfirstName = " + firstName +
                "\nlastName = " + lastName +
                "\nage = " + age +
                "\nemail = " + email +
                "\njobId = " + jobId + "\n";
    }
}
