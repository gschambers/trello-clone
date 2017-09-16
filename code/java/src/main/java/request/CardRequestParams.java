package request;

public class CardRequestParams {
    private int[] memberIds;
    private String query;
    private int[] tagIds;
    private CardRequestType type = CardRequestType.INTERSECTION;

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
