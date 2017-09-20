package card;

import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbCardListDao implements CardListDao {
    private final DataSource dataSource;

    @Inject
    public DbCardListDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CardList> getAllByBoardId(int boardId) throws RuntimeException {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT `id`, `title`, `order` "
                    + "FROM `lists` "
                    + "WHERE `boardId` = ? "
                    + "ORDER BY `order`"
            );
            stmt.setInt(1, boardId);
            ResultSet r = stmt.executeQuery();

            List<CardList> lists = new ArrayList<>();

            while (r.next()) {
                lists.add(createCardList(r));
            }

            return lists;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private CardList createCardList(ResultSet r) throws SQLException {
        CardList list = new CardList();
        list.setId(r.getInt("id"));
        list.setTitle(r.getString("title"));
        list.setOrder(r.getInt("order"));
        return list;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
