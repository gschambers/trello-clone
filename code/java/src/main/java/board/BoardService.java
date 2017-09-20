package board;

import card.*;
import tag.Color;
import tag.ColorDao;
import tag.Tag;
import tag.TagDao;
import com.google.inject.Inject;

import java.util.*;

public class BoardService {
    private final BoardDao boardDao;
    private final CardDao cardDao;
    private final CardListDao listDao;
    private final ColorDao colorDao;
    private final TagDao tagDao;

    @Inject
    public BoardService(
        BoardDao boardDao,
        CardDao cardDao,
        CardListDao listDao,
        ColorDao colorDao,
        TagDao tagDao
    ) {
        this.boardDao = boardDao;
        this.cardDao = cardDao;
        this.colorDao = colorDao;
        this.listDao = listDao;
        this.tagDao = tagDao;
    }

    public BoardDto getBoardById(int id) throws Exception {
        Board board = boardDao.getById(id)
            .orElseThrow(() -> new RuntimeException("Could not find board with ID " + id));

        int boardId = board.getId();

        HashMap<Integer, Card> cardMap = getCardMapByBoardId(boardId);
        LinkedHashMap<Integer, CardList> listMap = getCardListMapByBoardId(boardId);
        List<Integer> listIds = new ArrayList<>(listMap.keySet());
        HashMap<Integer, Color> colorMap = getColorMap();
        HashMap<Integer, Tag> tagMap = getTagMapByBoardId(boardId);

        BoardDto data = new BoardDto();
        data.setId(board.getId());
        data.setName(board.getName());
        data.setListIds(listIds);
        data.setCards(cardMap);
        data.setColors(colorMap);
        data.setLists(listMap);
        data.setTags(tagMap);
        return data;
    }

    private HashMap<Integer, Card> getCardMapByBoardId(int boardId) throws Exception {
        CardRequestDto params = new CardRequestDto();
        params.setBoardId(boardId);
        List<Card> cards = cardDao.getAll(params);
        HashMap<Integer, Card> cardMap = new HashMap<>();
        for (Card card : cards) cardMap.put(card.getId(), card);
        return cardMap;
    }

    private LinkedHashMap<Integer, CardList> getCardListMapByBoardId(int boardId) throws Exception {
        List<CardList> lists = listDao.getAllByBoardId(boardId);
        LinkedHashMap<Integer, CardList> listMap = new LinkedHashMap<>();
        for (CardList list : lists) listMap.put(list.getId(), list);
        return listMap;
    }

    private HashMap<Integer, Color> getColorMap() throws Exception {
        List<Color> colors = colorDao.getAll();
        HashMap<Integer, Color> colorMap = new HashMap<>();
        for (Color color : colors) colorMap.put(color.getId(), color);
        return colorMap;
    }

    private HashMap<Integer, Tag> getTagMapByBoardId(int boardId) throws Exception {
        List<Tag> tags = tagDao.getAllByBoardId(boardId);
        HashMap<Integer,  Tag> tagMap = new HashMap<>();
        for (Tag tag : tags) tagMap.put(tag.getId(), tag);
        return tagMap;
    }
}
