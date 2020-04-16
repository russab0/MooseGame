package com.company;


import java.util.Random;

public class RandomAgent implements Player {
    Random random = new Random();

    @Override
    public void reset() {
        this.random = new Random();
    }

    @Override
    public int move(int opponentLastMove, int xA, int xB, int xC) {
        return random.nextInt(3) + 1;;
    }

    @Override
    public String getEmail() {
        return "r.sabirov@innopolis.ru";
    }
}