package ca.cmpt213.a4.onlinehangman.model;

import java.util.Random;

public class Game {

    private long id;
    private Message message;
    private int numOfGuesses;
    private int numOfCorrectGuesses;
    private int numOfIncorrectGuesses;
    private final WordManager wordManager = WordManager.getSingleInstance();


    public Game(long id) { //dependency injection of the word
        this.id = id;
        this.numOfIncorrectGuesses = 0;
        this.numOfCorrectGuesses = 0;
        this.numOfGuesses = 0;
        setMessage();
    }

    private void setMessage() { //helper method
        Random random = new Random();
        int myRand = random.nextInt(WordManager.getNumOfWords());
        message = new Message((WordManager.getWordAt(myRand)));
        System.out.println(message.getMessage());
    }

    public Message getMessage() {
        return message;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
            return 1; // game is won

        return 0; //game is ongoing
    }
}
