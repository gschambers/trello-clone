import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.card.*;
import datamodel.Card;

import java.util.Optional;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Injector injector = Guice.createInjector(new TrelloModule());
        CardDao cardDao = injector.getInstance(CardDao.class);

        get("/cards",
            (req, res) -> cardDao.getAll(),
            gson::toJson);

        get("/cards/:id", (req, res) -> {
            Optional<Card> card = cardDao.getById(
                Integer.parseInt(req.params("id"))
            );

            if (!card.isPresent()) {
                res.status(404);
            }

            return card.orElse(null);
        }, gson::toJson);

        post("/cards", "application/json", (req, res) -> {
            Card card = gson.fromJson(req.body(), Card.class);

            if (cardDao.add(card)) {
                res.status(201);
            } else {
                res.status(409);
            }

            // TODO: return created resource
            return null;
        }, gson::toJson);
    }
}
