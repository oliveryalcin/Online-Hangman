package ca.cmpt213.a4.onlinehangman.controllers;

public class GameNotFound extends RuntimeException{
    public GameNotFound() {
    }

    public GameNotFound(String message) {
        super(message);
    }
}
