package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.GameManager;
import ca.cmpt213.a4.onlinehangman.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HangmanController {
    private final AtomicLong nextId = new AtomicLong();
    private Message promptMessage; //a reusable String object to display a prompt message at the screen
    private final GameManager gameManager = GameManager.getSingleInstance();
    private int currentIndex;
    Game currentGame;

    //works like a constructor, but wait until dependency injection is done, so it's more like a setup
    @PostConstruct
    public void hangmanControllerInit() {
        promptMessage = new Message("Initializing...");
    }

    @GetMapping("/helloworld")
    public String showHelloworldPage(Model model) {

        promptMessage.setMessage("You are at the helloworld page!");
        model.addAttribute("promptMessage", promptMessage);

        // take the user to helloworld.html
        return "helloworld";
    }

    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
        return "welcome"; //this is unnecessary ?
    }


    @GetMapping("/game")
    public String createGame(Model model) {

        currentIndex = (int) nextId.incrementAndGet() - 1;
        currentGame = new Game(currentIndex + 1);
        gameManager.add(currentGame);
        model.addAttribute("currentGame", currentGame);
        return "game";
    }

    @PostMapping("/game")
    public String playGame(@ModelAttribute("guess") Game currentGame, Model model) {

        gameManager.get(currentIndex).setGuess(currentGame.getGuess());
        currentGame = gameManager.get(currentIndex);
        currentGame.updateGameStatus();
        gameManager.set(currentIndex, currentGame);
        model.addAttribute("currentGame", currentGame);

        if (!currentGame.gameStatus().equals("Active")) {
            return "gameover";
        }

        return "game";
    }

    @GetMapping("game/{id}")
    public String gameWithId(@PathVariable("id") Long gameId) {
        for (Game game : gameManager) {
            if (game.getId() == gameId) {
                //show you won message if win or show u lost message
                return "game";
            }
        }

        throw new GameNotFound();
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "No such game found")
    @ExceptionHandler(GameNotFound.class)
    public void noSuchId() {
        //do nothing? I think w8
    }
}