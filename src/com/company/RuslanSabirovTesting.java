package com.company;

import java.util.ArrayList;

class Field {
    /*
        Auxilary class for dealing with fields.
     */
    int xA;
    int xB;
    int xC;

    public Field(int xA, int xB, int xC) {
        this.xA = xA;
        this.xB = xB;
        this.xC = xC;
    }

    public int getByMove(int move) {
        if (move == 1)
            return this.xA;
        if (move == 2)
            return this.xB;
        if (move == 3)
            return this.xC;
        return -1;
    }

    public void incByMove(int move, int delta) {
        if (move == 1)
            this.xA += delta;
        if (move == 2)
            this.xB += delta;
        if (move == 3)
            this.xC += delta;
    }

    public void updateByMoves(int move1, int move2) {
        incByMove(1, +1);
        incByMove(2, +1);
        incByMove(3, +1);
        if (move1 == move2) {
            if (getByMove(move1) >= 2)
                incByMove(move1, -2);
        }
        else {
            if (getByMove(move1) >= 2)
                incByMove(move1, -2);
            else if (getByMove(move1) == 1)
                incByMove(move1, -1);

            if (getByMove(move2) >= 2)
                incByMove(move2, -2);
            else if (getByMove(move2) == 1)
                incByMove(move2, -1);
        }
    }
}

public class RuslanSabirovTesting {
    final static int K = 500;

    public static double func(int x) {
        /*
            Computes the value of the function f(x)
         */
        double e = Math.E;
        double exp = Math.pow(e, x);
        return 10 * exp / (1 + exp);
    }

    public static double payoff(int move, Field field) {
        /*
            Computes the value of payoff: f(x) - f(0)
         */
        int x = field.getByMove(move);
        return func(x) - func(0);
    }

    public static double[] play(Player first, Player second) {
        /*
            Given two players it makes K moves and computes scores for each player
         */
        Field field = new Field(1, 1, 1);
        double firstScore = 0, secondScore = 0;
        int firstMove = 0, secondMove = 0;

        for (int m = 0; m < K; m++) {
            int prevFirstMove = firstMove;
            int prevSecondMove = secondMove;
            firstMove = first.move(prevSecondMove, field.xA, field.xB, field.xC);
            secondMove = second.move(prevFirstMove, field.xA, field.xB, field.xC);
            if (firstMove != secondMove) {
                firstScore += payoff(firstMove, field);
                secondScore += payoff(secondMove, field);
            }
            field.updateByMoves(firstMove, secondMove);
        }

        double[] ans = new double[2];
        ans[0] = firstScore;
        ans[1] = secondScore;
        return ans;
    }

    public static void main(String[] args) {
        /*
             In this method we create a list of all implemented agents and corresponding scores (both with length $N$).
             After that agents play against each other [N * (N - 1) / 2 games in total].
             Each game starts with reseting state of each agent, calling method play() and
             updating total scores for each player.
             After all games mean score for each agent is calculated.
         */
        System.out.println("GAMES BETWEEN EACH PAIR OF PLAYERS");
        ArrayList<Player> players = new ArrayList<>();
        players.add(new RandomAgent());
        players.add(new RandomNonEmptyAgent());
        players.add(new HighMidRandAgent());
        players.add(new GreedyAgent());
        players.add(new CopyCatAgent());
        players.add(new RuslanSabirovCode());
        players.add(new ProbabilisticPayoffAgent());
        double[] scores = new double[players.size()];

        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < i; j++) {
                players.get(i).reset();
                players.get(j).reset();
                double[] currentScores = play(players.get(i), players.get(j));
                scores[i] += currentScores[0];
                scores[j] += currentScores[1];
                System.out.println(players.get(i).getClass().getSimpleName() + ':' + players.get(j).getClass().getSimpleName() +
                        " | " + currentScores[0] + ':' + currentScores[1]);
            }
        }

        System.out.println();
        System.out.println("AVERAGE SCORES PER GAME:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(players.get(i).getClass().getSimpleName() + ": " + scores[i] / players.size());
        }
    }
}
