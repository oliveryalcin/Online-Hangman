package ca.cmpt213.a4.onlinehangman.controllers;

import ca.cmpt213.a4.onlinehangman.model.Game;
import ca.cmpt213.a4.onlinehangman.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class HangmanController {
    private final List<Game> games = new ArrayList<>(); //later on look into making GameManager class which will encapsulate everything
    private final AtomicLong nextId = new AtomicLong();
    private Message promptMessage; //a reusable String object to display a prompt message at the screen

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
    public void showWelcomePage(Model model) {
        //do nothing?
    }

    @PostMapping("/game")
    public void showGame(Model model) {
        //create game stuff here?
        Message message = new Message();
        Game currentGame = new Game(nextId.incrementAndGet());
        games.add(currentGame); //games array used elsewhere

    }

    @GetMapping("game/{id}")
    public void gameWithId(@PathVariable("id") Long gameId) {
        for (Game game : games) {
            if (game.getId() == gameId) {
                //show you won message if win or show u lost message
                return;
            }
        }

        throw new GameNotFound();
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "id not found")
    @ExceptionHandler(GameNotFound.class)
    public void noSuchId() {
        //do nothing? I think w8
    }
}