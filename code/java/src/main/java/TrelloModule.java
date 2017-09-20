import board.BoardDao;
import board.DbBoardDao;
import card.CardListDao;
import card.DbCardListDao;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
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
        bind(BoardDao.class).to(DbBoardDao.class);
        bind(CardDao.class).to(DbCardDao.class);
        bind(CardListDao.class).to(DbCardListDao.class);
        bind(ColorDao.class).to(DbColorDao.class);
        bind(TagDao.class).to(DbTagDao.class);
    }

    @Provides
    private DataSource provideDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/trello");
        dataSource.setUser("trello");
        dataSource.setPassword("letmein");
        return dataSource;
    }
}
