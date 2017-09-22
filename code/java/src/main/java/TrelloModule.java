import board.BoardDao;
import board.DbBoardDao;
import card.CardListDao;
import card.DbCardListDao;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import com.mysql.cj.jdbc.MysqlDataSource;
import card.CardDao;
import card.DbCardDao;
import tag.ColorDao;
import tag.DbColorDao;
import tag.TagDao;
import tag.DbTagDao;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class TrelloModule extends AbstractModule {
    private final Properties config = new Properties();

    public TrelloModule() {
        try {
            config.load(new FileReader("config.properties"));
        } catch (IOException ex) {
            System.err.println("Cannot load properties file");
        }
    }

    @Override
    protected void configure() {
        Names.bindProperties(binder(), config);
        bind(BoardDao.class).to(DbBoardDao.class);
        bind(CardDao.class).to(DbCardDao.class);
        bind(CardListDao.class).to(DbCardListDao.class);
        bind(ColorDao.class).to(DbColorDao.class);
        bind(TagDao.class).to(DbTagDao.class);
    }

    @Provides
    private DataSource provideDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(config.getProperty("jdbc.url"));
        dataSource.setUser(config.getProperty("jdbc.user"));
        dataSource.setPassword(config.getProperty("jdbc.password"));
        return dataSource;
    }
}
