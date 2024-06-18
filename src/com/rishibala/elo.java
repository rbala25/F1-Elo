package com.rishibala;

import java.util.List;

class elo {
    private static final int K = 32;

    public static void calculateElo(List<driver> drivers) {
        for (int i = 0; i < drivers.size(); i++) {
            driver currentDriver = drivers.get(i);
            for (int j = i + 1; j < drivers.size(); j++) {
                driver opponent = drivers.get(j);
                double expectedScoreCurrent = 1.0 / (1 + Math.pow(10, (opponent.getElo() - currentDriver.getElo()) / 400.0));
                double expectedScoreOpponent = 1 - expectedScoreCurrent;
//                double actualScoreCurrent = (i == 0) ? 1.0 : 0.0;
//                double actualScoreOpponent = 1 - actualScoreCurrent;
                double actualScoreCurrent = 1;
                double actualScoreOpponent = 0;
                double newRatingCurrent = currentDriver.getElo() + K * (actualScoreCurrent - expectedScoreCurrent);
                double newRatingOpponent = opponent.getElo() + K * (actualScoreOpponent - expectedScoreOpponent);
                currentDriver.setElo(newRatingCurrent);
                opponent.setElo(newRatingOpponent);
            }
        }
    }

//    public static void main(String[] args) {
        // examples
//        double driverA = 1500;
//        double driverB = 1542;
//
//        double expectedScoreA = expectedScore(driverA, driverB);
//        double expectedScoreB = expectedScore(driverB, driverA);
//
//
//        double actualScoreA = 1; //A beats B
//        double actualScoreB = 0;
//
//        driverA = updateRating(driverA, expectedScoreA, actualScoreA);
//        driverB = updateRating(driverB, expectedScoreB, actualScoreB);
//
//        System.out.println("New rating for Driver A: " + driverA);
//        System.out.println("New rating for Driver B: " + driverB);



//    }
}



