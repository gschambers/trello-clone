import com.google.gson.Gson;
import com.google.inject.Guice;
import com.google.inject.Injector;
import dao.card.*;
import datamodel.Card;
import java.util.Arrays;
import java.util.Optional;
import request.CardRequestParams;
import request.CardRequestType;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Injector injector = Guice.createInjector(new TrelloModule());
        CardDao cardDao = injector.getInstance(CardDao.class);

        get("/cards", (req, res) -> {
            CardRequestParams requestParams = new CardRequestParams();
            requestParams.setQuery(req.queryParams("query"));

            if (req.queryParams().contains("any")) {
                if (req.queryParams("any").equals("1")) {
                    requestParams.setType(CardRequestType.UNION);
                }
            }

            int[] tagIds = {};

            if (req.queryParams().contains("tag")) {
                tagIds = Arrays.stream(req.queryParamsValues("tag"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            }

            requestParams.setTagIds(tagIds);

            int[] memberIds = {};

            if (req.queryParams().contains("member")) {
                memberIds = Arrays.stream(req.queryParamsValues("member"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            }

            requestParams.setMemberIds(memberIds);

            return cardDao.getAll(requestParams);
        }, gson::toJson);

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

        // Log uncaught exceptions
        exception(Exception.class, (exception, req, res) -> {
            res.status(500);
            exception.printStackTrace();
        });
    }
}

