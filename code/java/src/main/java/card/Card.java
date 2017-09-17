package card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Serializable {
    private int id;
    private String title;
    private String description;
    private List<Integer> tagIds = new ArrayList<>();

    public Card() {}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Integer> tagIds) {
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return "Card{" + "id=" + getId() + ", title=" + getTitle() + "}";
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
