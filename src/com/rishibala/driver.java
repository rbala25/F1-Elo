package com.rishibala;

public class Driver {
    private final String firstName;
    private final String lastName;
    private double elo;
    private double highestElo;
    private int highestEloYear;
    private int highestEloRound;
    private String highestEloRace;
    private int countedRaces;

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

    public Driver(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        elo = 1500.0;
        highestElo = Double.MIN_VALUE;
        highestEloYear = 0;
        highestEloRound = 0;
        highestEloRace = "";
        countedRaces = 0;
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

    public Driver(String firstName, String lastName, double elo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.elo = elo;
        highestElo = Double.MIN_VALUE;
        highestEloYear = 0;
        highestEloRound = 0;
        countedRaces = 0;
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
        Driver dri = (Driver)obj;

        if ((dri.getFirstName().equals(firstName)) && (dri.getLastName().equals(lastName))) {
            return true;
        } else {
            return false;
        }
    }

    public String toHighestEloString() {
        return firstName + " " + lastName + ": " + highestElo + " ELO achieved in the " + highestEloYear + " " + highestEloRace + " (Round " + highestEloRound + ")";
    }

    public void increaseCountedRaces() {
        countedRaces++;
    }

    public int getCountedRaces() {
        return countedRaces;
    }
}
