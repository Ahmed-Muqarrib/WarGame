// package ca.sheridancollege.project;

public class AiPlayer extends Player {

    public AiPlayer() {
        super("aiPlayer");
    }

    @Override
    public Card playCard() {
        return getDeck().getCards().remove(0);
    }
}
