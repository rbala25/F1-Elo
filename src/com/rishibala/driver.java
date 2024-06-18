package com.rishibala;

public class driver {
    private final String firstName;
    private final String lastName;
    private double elo;

    public driver(String firstName, String lastName, double elo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.elo = elo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + elo;
    }

    public double getElo() {
        return elo;
    }

    @Override
    public int hashCode() {
        return firstName.hashCode()+lastName.hashCode();
    }

    public void setElo(double elo) {
        this.elo = elo;
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
