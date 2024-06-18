package com.rishibala;

class elo {
    private static final int K = 32;

    public static double expectedScore(double A, double B) {
        return 1 / (1 + Math.pow(10, (B - A) / 400));
    }

    public static double updateRating(double currentRating, double expectedScore, double actualScore) {
        return currentRating + K * (actualScore - expectedScore);
    }

    public static void main(String[] args) {
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



    }
}



