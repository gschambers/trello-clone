package tag;

import java.util.List;

public interface TagDao {
    /**
     * @return A list of all tags
     * @throws Exception DataSource exception
     */
    List<Tag> getAllByBoardId(int boardId) throws Exception;
}
