package ca.cmpt213.a4.onlinehangman.model;

public class Game {

    private long id;
    private Message message;
    private int numOfGuesses;
    private int numOfCorrectGuesses;
    private int numOfIncorrectGuesses;

    public Game(long id, Message message, int numOfGuesses, int numOfIncorrectGuesses) { //dependency injection of the word
        this.id = id;
        this.message = message;
        this.numOfGuesses = numOfGuesses;
        this.numOfIncorrectGuesses = numOfIncorrectGuesses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public void setNumOfGuesses(int numOfGuesses) {
        this.numOfGuesses = numOfGuesses;
    }

    public int getNumOfIncorrectGuesses() {
        return numOfIncorrectGuesses;
    }

    public void setNumOfIncorrectGuesses(int numOfIncorrectGuesses) {
        this.numOfIncorrectGuesses = numOfIncorrectGuesses;
    }

    public int gameStatus() {
        if (numOfIncorrectGuesses > 6)
            return -1; //game is lost

        if (numOfIncorrectGuesses < 6 && numOfCorrectGuesses == message.getMessage().length())
            return 1; // game is one

        return 0; //game is ongoing
    }
}
