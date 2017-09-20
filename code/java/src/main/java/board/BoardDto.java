package board;

import card.Card;
import card.CardList;
import tag.Color;
import tag.Tag;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class BoardDto implements Serializable {
    private int id;
    private Map<Integer, Card> cards;
    private Map<Integer, Color> colors;
    private List<Integer> listIds;
    private Map<Integer, CardList> lists;
    private String name;
    private Map<Integer, Tag> tags;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Card> getCards() {
        return cards;
    }

    public void setCards(Map<Integer, Card> cards) {
        this.cards = cards;
    }

    public Map<Integer, Color> getColors() {
        return colors;
    }

    public void setColors(Map<Integer, Color> colors) {
        this.colors = colors;
    }

    public List<Integer> getListIds() {
        return listIds;
    }

    public void setListIds(List<Integer> listIds) {
        this.listIds = listIds;
    }

    public Map<Integer, CardList> getLists() {
        return lists;
    }

    public void setLists(Map<Integer, CardList> lists) {
        this.lists = lists;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Tag> getTags() {
        return tags;
    }

    public void setTags(Map<Integer, Tag> tags) {
        this.tags = tags;
    }
}
