package com.hcg.ngg;

import com.hcg.ngg.generator.NumberGenerator;
import com.hcg.ngg.ground.GuessRound;
import com.hcg.ngg.player.Player;
import com.hcg.ngg.player.PlayerRound;

import java.util.*;

public class Main {

    private final static int PLAYER_NUMBER = 5;
    private final static int ROUND_NUMBER = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Player> playerList = new ArrayList<>();
        for (int i = 0; i < PLAYER_NUMBER; i++ ) {
            System.out.println("Please enter player name: ");
            playerList.add(new Player(scanner.nextLine()));
        }

        List<GuessRound> guessRoundList = new ArrayList<>();
        try {
            for (int r = 0; r < ROUND_NUMBER; r++) {
                String roundName = String.valueOf(r + 1);
                System.out.println("##### ROUND " + roundName + " BEGIN #####");
                GuessRound guessRound = new GuessRound(roundName, NumberGenerator.generate());
                List<Player> winners = new ArrayList<>();

                playerList.forEach(player -> {
                    System.out.println("Please player " + player.getName() + " to enter the guess value: ");

                    int guessValue = scanner.nextInt();
                    if (guessValue < 1 || guessValue > 10) {
                        throw new IllegalArgumentException("Error: guess value must between 1 and 10!");
                    }

                    PlayerRound playerRound = new PlayerRound(roundName, guessValue, guessRound.getGuessNumber());
                    player.getGuessRoundList().add(playerRound);

                    if (playerRound.getScore() == 1) {
                        winners.add(player);
                    }
                });

                guessRound.setWinnerList(winners);
                guessRoundList.add(guessRound);
                System.out.println("##### ROUND " + roundName + " END #####\n");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: guess value must be int value!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }

        System.out.println("##### ROUND RESULTS BEGIN #####");
        guessRoundList.forEach(System.out::println);
        System.out.println("##### ROUND RESULTS END #####\n");

        System.out.println("##### PLAYER RESULTS BEGIN #####");
        playerList.forEach(System.out::println);

        System.out.println("The Overall winners:");
        int maxScore = playerList.stream()
                .sorted(Comparator.comparing(Player::getScore).reversed())
                .map(Player::getScore)
                .findFirst()
                .orElse(0);
        playerList.stream()
                .filter(p -> p.getScore() == maxScore)
                .forEach(p -> System.out.println("Player: " + p.getName()));

        System.out.println("##### PLAYER RESULTS END #####");

    }

}
