package board;

import java.util.Optional;

public interface BoardDao {
    Optional<Board> getById(int id) throws Exception;
}
