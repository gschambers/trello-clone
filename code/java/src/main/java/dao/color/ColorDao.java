package dao.color;

import datamodel.Color;
import java.util.List;

public interface ColorDao {
    /**
     * @return A list of all colors
     * @throws Exception A DataSource exception
     */
    List<Color> getAll() throws Exception;
}
