package card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CardList implements Serializable {
    private int id;
    private String title;
    private List<Integer> cardIds = new ArrayList<>();
    private int order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "CardList{" + "id=" + getId() + ", name=" + getTitle() + "}";
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        final Card card = (Card) other;

        return hashCode() == card.hashCode();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
