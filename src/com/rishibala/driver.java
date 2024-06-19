package com.rishibala;

public class driver {
    private final String firstName;
    private final String lastName;
    private double elo;
    private double highestElo;
    private int highestEloYear;
    private int highestEloRound;
    private String highestEloRace;

//    public double getRatingDeviation() {
//        return ratingDeviation;
//    }
//
//    public void setRatingDeviation(double ratingDeviation) {
//        this.ratingDeviation = ratingDeviation;
//    }
//
//    public double getVolatility() {
//        return volatility;
//    }
//
//    public void setVolatility(double volatility) {
//        this.volatility = volatility;
//    }
//
//    private double ratingDeviation;
//    private double volatility;

    public double getHighestElo() {
        return highestElo;
    }

    public void setHighestElo(double highestElo) {
        this.highestElo = highestElo;
    }

    public String getHighestEloRace() {
        return highestEloRace;
    }

    public void setHighestEloRace(String highestEloRace) {
        this.highestEloRace = highestEloRace;
    }

    public driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        elo = 1500.0;
        highestElo = Double.MIN_VALUE;
        highestEloYear = 0;
        highestEloRound = 0;
        highestEloRace = "";
//        ratingDeviation = 350.0;
//        volatility = 0.06;
    }

    public int getHighestEloYear() {
        return highestEloYear;
    }

    public int getHighestEloRound() {
        return highestEloRound;
    }

    public void setHighestEloYear(int highestEloYear) {
        this.highestEloYear = highestEloYear;
    }

    public void setHighestEloRound(int highestEloRound) {
        this.highestEloRound = highestEloRound;
    }

    public driver(String firstName, String lastName, double elo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.elo = elo;
        highestElo = Double.MIN_VALUE;
        highestEloYear = 0;
        highestEloRound = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + elo + " ELO";
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

    public String toHighestEloString() {
        return firstName + " " + lastName + ": " + highestElo + " ELO achieved in the " + highestEloYear + " " + highestEloRace + " (Round " + highestEloRound + ")";
    }
}
