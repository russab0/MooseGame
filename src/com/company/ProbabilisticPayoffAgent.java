package com.company;


import java.util.Random;

public class ProbabilisticPayoffAgent implements Player {
    Random random = new Random();

    private boolean isNotEmpty(int choice, int xA, int xB, int xC) {
        if (choice == 1)
            return xA > 0;
        if (choice == 2)
            return xB > 0;
        if (choice == 3)
            return xC > 0;
        return false;
    }

    @Override
    public void reset() {
        this.random = new Random();
    }

    public static double func(int x) {
        double e = Math.E;
        double exp = Math.pow(e, x);
        return 10 * exp / (1 + exp);
    }

    public static double payoff(int xK) {
        return func(xK) - func(0);
    }

    @Override
    public int move(int opponentLastMove, int xA, int xB, int xC) {
        if (xA + xB + xC == 0)
            return random.nextInt(3) + 1;

        double[] proportion = new double[3];
        double totalPayoff = payoff(xA) + payoff(xB) + payoff(xC);
        proportion[0] = 1. * payoff(xA) / totalPayoff;
        proportion[1] = 1. * payoff(xA) / totalPayoff;
        proportion[2] = 1. * payoff(xA) / totalPayoff;
        double prob = Math.random();
        if (prob < proportion[0])
            return 1;
        if (prob < proportion[1])
            return 2;
        return 3;
    }

    @Override
    public String getEmail() {
        return "r.sabirov@innopolis.ru";
    }
}