package card;

import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DbCardDao implements CardDao {
    private final DataSource dataSource;

    @Inject
    public DbCardDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Card> getAll(CardRequestParams requestParams) throws RuntimeException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement stmt1 = conn.prepareStatement(
                "SELECT `id`, `title`, `description` "
                    + "FROM `cards`"
            );
            ResultSet r1 = stmt1.executeQuery();

            PreparedStatement stmt2 = conn.prepareStatement(
                "SELECT `cardId`, `tagId` "
                    + "FROM `cards_tags`"
            );
            ResultSet r2 = stmt2.executeQuery();

            HashMap<Integer, List<Integer>> tagMap = new HashMap<>();

            while (r2.next()) {
                Integer cardId = r2.getInt("cardId");
                tagMap.putIfAbsent(cardId, new ArrayList<>());
                tagMap.get(cardId).add(r2.getInt("tagId"));
            }

            List<Card> cards = new ArrayList<>();

            while (r1.next()) {
                Card card = createCard(r1);
                card.setTagIds(tagMap.getOrDefault(card.getId(), new ArrayList<>()));
                cards.add(card);
            }

            return cards;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public Optional<Card> getById(int id) throws RuntimeException {
        try {
            Connection conn = this.getConnection();

            PreparedStatement stmt1 =
                conn.prepareStatement(
                    "SELECT `id`, `title`, `description` "
                        + "FROM `cards` "
                        + "WHERE `id` = ?"
                );
            stmt1.setInt(1, id);
            ResultSet r1 = stmt1.executeQuery();

            PreparedStatement stmt2 =
                conn.prepareStatement(
                    "SELECT `tagId`"
                        + "FROM `cards_tags` "
                        + "WHERE `cardId` = ?"
                );
            stmt2.setInt(1, id);
            ResultSet r2 = stmt2.executeQuery();

            List<Integer> tagIds = new ArrayList<>();

            while (r2.next()) {
                tagIds.add(r2.getInt("tagId"));
            }

            if (r1.next()) {
                Card card = createCard(r1);
                card.setTagIds(tagIds);
                return Optional.of(card);
            }

            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean add(Card card) throws RuntimeException {
        if (getById(card.getId()).isPresent()) {
            return false;
        }

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement =
                conn.prepareStatement(
                    "INSERT INTO `cards` (`title`, `description`) "
                        + "VALUES (?, ?)"
                );
            statement.setString(1, card.getTitle());
            statement.setString(2, card.getDescription());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean update(Card card) {
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement =
                conn.prepareStatement(
                    "UPDATE `cards` "
                        + "SET `title` = ?, `description` = ? "
                        + "WHERE `id` = ?"
                );
            statement.setString(1, card.getTitle());
            statement.setString(2, card.getDescription());
            statement.setInt(3, card.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public boolean delete(Card card) {
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement =
                conn.prepareStatement("DELETE FROM `cards` WHERE `id` = ?");
            statement.setInt(1, card.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private Card createCard(ResultSet r) throws SQLException {
        Card card = new Card();
        card.setId(r.getInt("id"));
        card.setTitle(r.getString("title"));
        card.setDescription(r.getString("description"));
        return card;
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
