package com.company;


import java.util.Random;

public class CopyCatAgent implements Player {
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

    @Override
    public int move(int opponentLastMove, int xA, int xB, int xC) {
        int choice = opponentLastMove;
        while (!isNotEmpty(choice, xA, xB, xC)) {
            choice = random.nextInt(3) + 1;
        }
        return choice;
    }

    @Override
    public String getEmail() {
        return "r.sabirov@innopolis.ru";
    }
}