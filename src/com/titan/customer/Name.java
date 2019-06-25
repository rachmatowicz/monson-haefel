package com.titan.customer;

public class Name implements java.io.Serializable {
    private String lastname;
    private String firstName;

    public Name(String lastname, String firstName) {
        this.lastname = lastname;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public String getFirstName() {
        return firstName;
    }
}
