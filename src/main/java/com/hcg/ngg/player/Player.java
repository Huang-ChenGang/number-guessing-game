package com.hcg.ngg.player;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Player {

    private String name;

    private List<PlayerRound> guessRoundList;

    public Player(String name) {
        this.name = name;
        this.guessRoundList = new ArrayList<>();
    }

    public int getScore() {
        return this.guessRoundList.stream()
                .mapToInt(PlayerRound::getScore)
                .sum();
    }

    public String toString() {
        String guessRoundResult = this.guessRoundList.stream()
                .map(PlayerRound::toString)
                .collect(Collectors.joining("\n"));
        return "Player name: " + this.name + "\n"
                + guessRoundResult + "\n"
                + "Your score is: " + getScore() + "\n";
    }
}
