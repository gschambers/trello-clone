package board;

import com.google.gson.Gson;
import com.google.inject.Inject;

import static spark.Spark.*;

public class BoardController {
    private final BoardService boardService;

    @Inject
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    public void configure() {
        Gson gson = new Gson();

        get("/boards/:id", (req, res) -> {
            int id = Integer.parseInt(req.params("id"));
            return boardService.getBoardById(id);
        }, gson::toJson);
    }
}
