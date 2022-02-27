package com.hcg.ngg.player;

import lombok.Data;

@Data
public class PlayerRound {

    private String roundName;

    private int guessValue;

    private int correctValue;

    public PlayerRound(String roundName, int guessValue, int correctValue) {
        this.roundName = roundName;
        this.guessValue = guessValue;
        this.correctValue = correctValue;
    }

    public int getScore() {
        return this.guessValue == correctValue ? 1 : 0;
    }

    public String toString() {
        return "Round " + this.roundName + " Guess value: " + this.guessValue + ", Correct value: " + this.correctValue;
    }
}
