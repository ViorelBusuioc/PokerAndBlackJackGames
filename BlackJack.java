package dev.lpa;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class BlackJack {

    public static void main(String[] args) {

        List<Card> deck = Card.getStandardDeck();
//        Card.printDeck(deck);
        Collections.shuffle(deck);
        Collections.rotate(deck, 26);

//        Card.printDeck(deck);

        blackjackGame(deck);
    }

    public static void blackjackGame(List<Card> deck) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int total = 0;
        for (var card : deck) {
            System.out.print(card);
            try {
                total += Integer.parseInt(card.face());
            } catch (NumberFormatException e) {
                switch (card.face()) {
                    case "J", "Q", "K" -> total += 10;
                    case "A" -> total += 1;
                }
            }
            System.out.println(" (Total = " + total + ")");
            if (total == 21) {
                System.out.println("BLACKJACK!!!");
                break;
            } else if (total < 21) {
                System.out.println("You can call another card");
            } else {
                System.out.println("Over! Game ended.");
                break;
            }
            System.out.println("""
                   N -> Call Another Card;
                   S -> Stop;""");
            String nextCard = scanner.nextLine();
            if (nextCard.equals("S")) {
                System.out.println("Your hand: " + total);
                int dealerHand = random.nextInt(14, 21);
                if (total > dealerHand) {
                    System.out.println("Dealer hand: " + dealerHand);
                    System.out.println("YOU WON!");
                    break;
                } else if (total < dealerHand) {
                    System.out.println("Dealer hand: " + dealerHand);
                    System.out.println("YOU LOST!");
                    break;
                } else
                    System.out.println("Dealer hand: " + dealerHand);
                    System.out.println("Same hands. That's a tie!");
            }
        }
    }
}
