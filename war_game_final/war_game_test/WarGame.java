// package ca.sheridancollege.project;

import java.util.Scanner;

public class WarGame extends Game {

    private GroupOfCards deck;
    private Player player;
    private AiPlayer aiPlayer;

    public WarGame(String name) {
        super(name);
        player = new Player(name) {
            @Override
            public Card playCard() {
                return getDeck().getCards().remove(0);
            }
        };
        aiPlayer = new AiPlayer();
        initializeDeck();
        dealCards();
    }

    private void initializeDeck() {
        deck = new GroupOfCards(52);
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                deck.getCards().add(new Card(value, suit));
            }
        }
        deck.shuffle();
    }

    private void dealCards() {
        for (int i = 0; i < 26; i++) {
            player.addCardToDeck(deck.getCards().get(i));
            aiPlayer.addCardToDeck(deck.getCards().get(i + 26));
        }
    }

    @Override
    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to War Game!");
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();
        player.setName(playerName);
        System.out.println("Type 'start' to begin the game.");
        String start = scanner.nextLine().toLowerCase();

        if (start.equals("start")) {
            System.out.println("Let the game begin!");

            for (int round = 1; round <= 10; round++) {
                
                System.out.println("\nRound " + round);
                playRound();

                if (round >= 10) {
                    player.addWonDeckToDeck();
                    aiPlayer.addWonDeckToDeck();
                }
            }

            declareWinner();
        } else {
            System.out.println("Goodbye!");
        }
    }

    private void playRound() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.getName() + "'s deck size: " + player.getDeckSize());
        System.out.println("AI's deck size: " + aiPlayer.getDeckSize());
        System.out.println("Type 'play' or 'p' to play the round.");
        String playCommand = scanner.nextLine().toLowerCase();

        if (playCommand.equals("play") || playCommand.equals("p")) {
            Card playerCard = player.playCard();
            Card aiCard = aiPlayer.playCard();

            System.out.println("\n-----------------------------------------------------");
            System.out.println(player.getName() + "'s card: " + playerCard);
            System.out.println("AI's card: " + aiCard);

            if (playerCard.getValue().ordinal() > aiCard.getValue().ordinal()) {
                player.addCardToWonDeck(playerCard);
                player.addCardToWonDeck(aiCard);
                System.out.println(player.getName() + " wins this round!");
                System.out.println("-----------------------------------------------------\n");
            } else if (playerCard.getValue().ordinal() < aiCard.getValue().ordinal()) {
                aiPlayer.addCardToWonDeck(playerCard);
                aiPlayer.addCardToWonDeck(aiCard);
                System.out.println("AI wins this round!");
                System.out.println("-----------------------------------------------------\n");
            } else {
                System.out.println("It's a tie! WAR!");

                while (playerCard.getValue().ordinal() == aiCard.getValue().ordinal()) {
                    playerCard = player.playCard();
                    aiCard = aiPlayer.playCard();

                    System.out.println("\n-----------------------------------------------------");
                    System.out.println(player.getName() + "'s card: " + playerCard);
                    System.out.println("AI's card: " + aiCard);
                }

                if (playerCard.getValue().ordinal() > aiCard.getValue().ordinal()) {
                    player.addCardToWonDeck(playerCard);
                    player.addCardToWonDeck(aiCard);
                    System.out.println(player.getName() + " wins this round!");
                    System.out.println("-----------------------------------------------------\n");
                } else {
                    aiPlayer.addCardToWonDeck(playerCard);
                    aiPlayer.addCardToWonDeck(aiCard);
                    System.out.println("AI wins this round!");
                    System.out.println("-----------------------------------------------------\n");
                }
            }

            System.out.println("======================== DECK INFO ========================");
            System.out.println(player.getName() + "'s deck size: " + player.getDeckSize());
            System.out.println("AI's deck size: " + aiPlayer.getDeckSize());
            System.out.println();
            System.out.println(player.getName() + "'s Won deck size: " + player.getWonDeckSize());
            System.out.println("AI's Won deck size: " + aiPlayer.getWonDeckSize());
            System.out.println("=============================================================");

        } else {
            System.out.println("Invalid command. Please type 'play' or 'p' to play the round.");
        }
    }

    @Override
    public void declareWinner() {
        while (player.getDeckSize() == aiPlayer.getDeckSize()) {
            System.out.println("\nTied after 10 rounds! Playing wild round...");
            playRound();
            player.addWonDeckToDeck();
            aiPlayer.addWonDeckToDeck();
        }

        if (player.getDeckSize() > aiPlayer.getDeckSize()) {
            System.out.println("SUCCESSFULLY ADDED WON DECKS TO MAIN DECKS\n");
            System.out.println(player.getName() + "'s FINAL deck size: " + player.getDeckSize());
            System.out.println("AI's FINAL deck size: " + aiPlayer.getDeckSize());
            System.out.println();
            System.out.println(player.getName() + " is the WINNER! <<<<<<<<<<<<<<<<<<<");
        } else {
            System.out.println("SUCCESSFULLY ADDED WON DECKS TO MAIN DECKS\n");
            System.out.println(player.getName() + "'s FINAL deck size: " + player.getDeckSize());
            System.out.println("AI's FINAL deck size: " + aiPlayer.getDeckSize());
            System.out.println();
            System.out.println("AI is the WINNER! <<<<<<<<<<<<<<<<<<<");
        }
    }

    public static void main(String[] args) {
        WarGame warGame = new WarGame("Player");
        warGame.play();
    }
}
