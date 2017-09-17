package tag;

import com.google.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbColorDao implements ColorDao {
    private DataSource dataSource;

    @Inject
    public DbColorDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Color> getAll() throws RuntimeException {
        try {
            Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT `id`, `hexCode` FROM `colors`"
            );
            ResultSet r = stmt.executeQuery();

            List<Color> colors = new ArrayList<>();

            while (r.next()) {
                colors.add(createColor(r));
            }

            return colors;
        } catch(SQLException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    private Color createColor(ResultSet r) throws SQLException {
        Color color = new Color();
        color.setId(r.getInt("id"));
        color.setHexCode(r.getString("hexCode"));
        return color;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
