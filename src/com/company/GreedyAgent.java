package com.company;


public class GreedyAgent implements Player {
    @Override
    public void reset() {
    }

    @Override
    public int move(int opponentLastMove, int xA, int xB, int xC) {
        int maxi = Math.max(Math.max(xA, xB), xC);
        if (xA == maxi)
            return 1;
        if (xB == maxi)
            return 2;
        return 3;
    }
}