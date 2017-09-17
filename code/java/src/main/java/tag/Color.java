package tag;

import card.Card;

import java.io.Serializable;

public class Color implements Serializable {
    private int id;
    private String hexCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    @Override
    public String toString() {
        return "Color{" + "id=" + getId() + ", hexCode=" + getHexCode() + "}";
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
