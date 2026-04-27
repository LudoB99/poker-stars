package cegepst.api;

import cegepst.api.dto.ActionRequest;
import cegepst.api.dto.GameStateResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<GameStateResponse> start(HttpSession session) {
        return ResponseEntity.ok(gameService.startNewGame(session));
    }

    @PostMapping("/action")
    public ResponseEntity<GameStateResponse> action(@RequestBody ActionRequest request, HttpSession session) {
        try {
            return ResponseEntity.ok(gameService.processAction(request.action(), session));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
