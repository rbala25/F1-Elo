package com.rishibala;

public class driver {
    private final String firstName;
    private final String lastName;

    public driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Driver: " + firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode()+lastName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        driver dri = (driver)obj;

        if ((dri.getFirstName().equals(firstName)) && (dri.getLastName().equals(lastName))) {
            return true;
        } else {
            return false;
        }
    }
}
