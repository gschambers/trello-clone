import com.google.inject.AbstractModule;
import com.mysql.cj.jdbc.MysqlDataSource;
import dao.card.CardDao;
import dao.card.DbCardDao;
import dao.color.ColorDao;
import dao.color.DbColorDao;
import dao.tag.TagDao;
import dao.tag.DbTagDao;

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
