package datamodel;

import java.io.Serializable;

public class List implements Serializable {
    private int id;
    private String title;
    private int[] cardIds;

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

    @Override
    public String toString() {
        return "List{" + "id=" + getId() + ", title=" + getTitle() + "}";
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
