package com.company;


import java.util.LinkedList;
import java.util.Random;

public class HighMidRandAgent implements Player {
    LinkedList<Integer> myPreviousMoves = new LinkedList<>();
    Random random = new Random();

    @Override
    public void reset() {
    }

    public int getHigh(int xA, int xB, int xC) {
        if (xA >= xB && xA >= xC) return 1;
        if (xB >= xA && xB >= xC) return 2;
        return 3;
    }

    public int getLow(int xA, int xB, int xC) {
        if (xA <= xB && xA <= xC) return 1;
        if (xB <= xA && xB <= xC) return 2;
        return 3;
    }

    public int getMid(int xA, int xB, int xC) {
        if (xB <= xA && xA <= xC || xB >= xA && xA >= xC) return 1;
        if (xA <= xB && xB <= xC || xA >= xB && xB >= xC) return 2;
        return 3;
    }

    @Override
    public int move(int opponentLastMove, int xA, int xB, int xC) {
        int move;
        if (random.nextInt(1) == 1)
            move = getHigh(xA, xB, xC);
        else
            move = getMid(xA, xB, xC);
        return move;
    }

    @Override
    public String getEmail() {
        return "r.sabirov@innopolis.ru";
    }
}