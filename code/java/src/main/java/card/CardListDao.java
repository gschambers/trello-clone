package card;

import java.util.List;

public interface CardListDao {
    List<CardList> getAllByBoardId(int boardId) throws Exception;
}
