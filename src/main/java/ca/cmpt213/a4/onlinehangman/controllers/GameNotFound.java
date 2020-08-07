package ca.cmpt213.a4.onlinehangman.controllers;

/**
 * Custom exception class used for when there is no game matching the given id (the game does not exist)
 * Student id: 301350814
 * Email: owells@sfu.ca
 */
public class GameNotFound extends RuntimeException {

    public GameNotFound() {
        super();
    }

    public GameNotFound(String message) {
        super(message);
    }
}
