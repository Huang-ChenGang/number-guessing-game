package com.hcg.ngg.ground;

import com.hcg.ngg.player.Player;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GuessRound {

    private String name;

    private int guessNumber;

    private List<Player> winnerList;

    public GuessRound(String name, int guessNumber) {
        this.name = name;
        this.guessNumber = guessNumber;
    }

    public String toString() {
        String winnerResult = "No winner";
        if (this.winnerList != null && this.winnerList.size() > 0) {
            winnerResult = winnerList.stream()
                    .map(p -> "Player: " + p.getName())
                    .collect(Collectors.joining("; "));
        }

        return "guess number for round " + this.name + ": " + this.guessNumber + "\n"
                + "winners: " + winnerResult + "\n";
    }
}
