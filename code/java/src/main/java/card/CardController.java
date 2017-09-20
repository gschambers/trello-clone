package card;

import com.google.gson.Gson;
import com.google.inject.Inject;

import java.util.Optional;

import static spark.Spark.*;

public class CardController {
    private final CardDao cardDao;

    @Inject
    public CardController(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public void configure() {
        Gson gson = new Gson();

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
