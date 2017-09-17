package dao.tag;

import datamodel.Tag;
import java.util.List;

public interface TagDao {
    /**
     * @return A list of all tags
     * @throws Exception DataSource exception
     */
    List<Tag> getAll() throws Exception;
}
