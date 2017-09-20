package card;

import java.io.Serializable;
import java.util.Optional;

public class CardRequestDto implements Serializable {
    private Optional<Integer> boardId = Optional.empty();
    private int[] memberIds;
    private String query;
    private int[] tagIds;
    private CardRequestType type = CardRequestType.INTERSECTION;

    public Optional<Integer> getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = Optional.of(boardId);
    }

    public void setMemberIds(int[] memberIds) {
        this.memberIds = memberIds;
    }

    public int[] getMemberIds() {
        return memberIds;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setTagIds(int[] tagIds) {
        this.tagIds = tagIds;
    }

    public int[] getTagIds() {
        return tagIds;
    }

    public void setType(CardRequestType type) {
        this.type = type;
    }

    public CardRequestType getType() {
        return type;
    }
}
