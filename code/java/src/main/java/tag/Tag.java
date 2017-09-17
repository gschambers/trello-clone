package tag;

import card.Card;

import java.io.Serializable;

public class Tag implements Serializable {
    private int id;
    private String name;
    private int colorId;
    private boolean hidden;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean isHidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + getId() + ", hexCode=" + getName() + "}";
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
