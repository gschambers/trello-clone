import com.google.inject.AbstractModule;
import com.mysql.cj.jdbc.MysqlDataSource;
import card.CardDao;
import card.DbCardDao;
import tag.ColorDao;
import tag.DbColorDao;
import tag.TagDao;
import tag.DbTagDao;

import javax.sql.DataSource;

public class TrelloModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataSource.class).toInstance(this.getDataSource());
        bind(CardDao.class).to(DbCardDao.class);
        bind(ColorDao.class).to(DbColorDao.class);
        bind(TagDao.class).to(DbTagDao.class);
    }

    private DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/trello");
        dataSource.setUser("trello");
        dataSource.setPassword("letmein");
        return dataSource;
    }
}
