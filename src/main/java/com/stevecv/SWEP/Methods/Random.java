package com.stevecv.SWEP.Methods;

public class Random {
    public static boolean ifChance(double input) {
        double r = Math.random();
        return r < input/100;
    }

    public int randomRound(double max, double min) {
        java.util.Random random = new java.util.Random();
        int randomNumber = (int) Math.round((random.nextInt((int) Math.round(max - min)) + min));

        return randomNumber;
    }
}
