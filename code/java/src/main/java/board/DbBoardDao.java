package board;

import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DbBoardDao implements BoardDao {
    private final DataSource dataSource;

    @Inject
    public DbBoardDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Optional<Board> getById(int id) throws RuntimeException {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT `id`, `name` "
                    + "FROM `boards` "
                    + "WHERE `id` = ?"
            );
            stmt.setInt(1, id);
            ResultSet r = stmt.executeQuery();

            if (r.next()) {
                return Optional.of(createBoard(r));
            }

            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private Board createBoard(ResultSet r) throws SQLException {
        Board board = new Board();
        board.setId(r.getInt("id"));
        board.setName(r.getString("name"));
        return board;
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
