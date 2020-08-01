package ca.cmpt213.a4.onlinehangman.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as a database for Game objects
 */
public class GameManager {

    private static List<Game> games;
    private static GameManager single_instance;

    private GameManager() {
        games = new ArrayList<>();
    }

    public static GameManager getSingleInstance() {
        if (single_instance == null) {
            single_instance = new GameManager();
        }

        return single_instance;
    }

    public Game get(int index) {
        return games.get(index);
    }

    public void set(int index, Game game) {
        games.set(index, game);
    }
    public void add(Game game){
        games.add(game);
    }


}
