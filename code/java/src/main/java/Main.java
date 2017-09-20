import board.BoardController;
import card.CardController;
import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Injector injector = Guice.createInjector(new TrelloModule());
        BoardController boardController = injector.getInstance(BoardController.class);
        CardController cardController = injector.getInstance(CardController.class);

        boardController.configure();
        cardController.configure();

//
//        get("/tags", (req, res) -> tagDao.getAll(), gson::toJson);
//        get("/colors", (req, res) -> colorDao.getAll(), gson::toJson);

        // Log uncaught exceptions
        exception(Exception.class, (exception, req, res) -> {
            res.status(500);
            exception.printStackTrace();
        });
    }
}

