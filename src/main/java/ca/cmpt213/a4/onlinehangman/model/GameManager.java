package ca.cmpt213.a4.onlinehangman.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Acts as a database for Game objects and encapsulates some functionalities
 *
 * Student id: 301350814
 * Email: owells@sfu.ca
 */
public class GameManager implements Iterable<Game> {

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

    public void add(Game game) {
        games.add(game);
    }


    @Override
    public Iterator<Game> iterator() {
        return games.iterator();
    }
}
