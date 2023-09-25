// package ca.sheridancollege.project;

import java.util.List;

public abstract class Player {

    private String name;
    private GroupOfCards deck;
    private GroupOfCards wonDeck;

    public Player(String name) {
        this.name = name;
        this.deck = new GroupOfCards(26);
        this.wonDeck = new GroupOfCards(52);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public GroupOfCards getDeck() {
        return deck;
    }

    public void addCardToDeck(Card card) {
        deck.getCards().add(card);
    }

    public GroupOfCards getWonDeck() {
        return wonDeck;
    }

    public void addCardToWonDeck(Card card) {
        wonDeck.getCards().add(card);
    }

    public int getDeckSize() {
        return deck.getCards().size();
    }

    public int getWonDeckSize() {
        return wonDeck.getCards().size();
    }

    public abstract Card playCard();

    public void shuffleDeck() {
        deck.shuffle();
    }

    public void addWonDeckToDeck() {
        getDeck().addAll(getWonDeck().getCards());
        getWonDeck().clear();
    }


}
