package com.rishibala;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//    HashMap<driver, Double> drivers = getDrivers.getDrivers();

        List<Driver> driversList = sortFinalOrder(GetResults.rankAll());
        System.out.println();
        System.out.println();
        System.out.println("Formula 1 Rankings by Final ELO (1950-2024): ");

        int counter = 1;
        for(Driver driver : driversList) {
            System.out.println(counter + ". " + driver);
            counter++;
        }

        driversList = sortHighestOrder(driversList);
        System.out.println();
        System.out.println();
        System.out.println("Formula 1 Rankings by Highest Achieved ELO (1950-2024): ");
        System.out.println("**Minimum 3 Races**");

        int counter1 = 1;
        for(Driver driver : driversList) {
            if(driver.getCountedRaces() < 3) {
                continue;
            }

            System.out.println(counter1 + ". " + driver.toHighestEloString());
            counter1++;
        }
    }

    static List<Driver> sortFinalOrder(List<Driver> driversList) {

        driversList.sort(new Comparator<Driver>() {
            @Override
            public int compare(Driver d1, Driver d2) {
                //descending order
                return Double.compare(d2.getElo(), d1.getElo());
            }
        });

        return driversList;
    }

    static List<Driver> sortHighestOrder(List<Driver> driversList) {
        driversList.sort(new Comparator<Driver>() {
            @Override
            public int compare(Driver d1, Driver d2) {
                //descending order
                return Double.compare(d2.getHighestElo(), d1.getHighestElo());
            }
        });

        return driversList;
    }

}