import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GroupOfCards {

    private ArrayList<Card> cards;
    private int size;

    public GroupOfCards(int size) {
        this.size = size;
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addAll(List<Card> cardsToAdd) {
        cards.addAll(cardsToAdd);
    }

    public void clear() {
        cards.clear();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
