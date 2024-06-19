package com.rishibala;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//    HashMap<driver, Double> drivers = getDrivers.getDrivers();

        List<driver> driversList = sortFinalOrder(getResults.rankAll());
        System.out.println();
        System.out.println();
        System.out.println("Formula 1 Rankings by Final ELO (1950-2024): ");

        int counter = 1;
        for(driver driver : driversList) {
            System.out.println(counter + ". " + driver);
            counter++;
        }

        driversList = sortHighestOrder(driversList);
        System.out.println();
        System.out.println();
        System.out.println("Formula 1 Rankings by Highest Achieved ELO (1950-2024): ");
        System.out.println("**Minimum 3 Races**");

        int counter1 = 1;
        for(driver driver : driversList) {
            if(driver.getCountedRaces() < 3) {
                continue;
            }

            System.out.println(counter1 + ". " + driver.toHighestEloString());
            counter1++;
        }
    }

    static List<driver> sortFinalOrder(List<driver> driversList) {

        driversList.sort(new Comparator<driver>() {
            @Override
            public int compare(driver d1, driver d2) {
                //descending order
                return Double.compare(d2.getElo(), d1.getElo());
            }
        });

        return driversList;
    }

    static List<driver> sortHighestOrder(List<driver> driversList) {
        driversList.sort(new Comparator<driver>() {
            @Override
            public int compare(driver d1, driver d2) {
                //descending order
                return Double.compare(d2.getHighestElo(), d1.getHighestElo());
            }
        });

        return driversList;
    }

}