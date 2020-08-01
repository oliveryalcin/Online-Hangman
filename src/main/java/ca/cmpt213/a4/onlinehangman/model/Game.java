package ca.cmpt213.a4.onlinehangman.model;

import java.io.Serializable;
import java.util.Random;

public class Game implements Serializable {

    private String guess;
    private String guessHistory;
    private long id;
    private Message message;
    private int numOfGuesses;
    private int numOfCorrectGuesses; //will use to keep track of game progress or maybe i can just compare strings idk
    private int numOfIncorrectGuesses;
    private final WordManager wordManager = WordManager.getSingleInstance();


    public Game(long id) { //dependency injection of the word

        this.id = id;
        this.numOfIncorrectGuesses = 0;
        this.numOfCorrectGuesses = 0;
        this.numOfGuesses = 0;
        initializeGameWord();
    }

    public Game() {
        this.id = 0;
        this.numOfIncorrectGuesses = 0;
        this.numOfCorrectGuesses = 0;
        this.numOfGuesses = 0;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setNumOfCorrectGuesses(int numOfCorrectGuesses) {
        this.numOfCorrectGuesses = numOfCorrectGuesses;
    }

    private void initializeGameWord() { //helper method
        Random random = new Random();
        int myRand = random.nextInt(WordManager.getNumOfWords());
        message = new Message((WordManager.getWordAt(myRand)));
        System.out.println(message.getMessage());
        System.out.println(message.getCensoredMessage());
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

    public int getNumOfCorrectGuesses() {
        return numOfCorrectGuesses;
    }

    public int getNumOfGuesses() {
        return numOfGuesses;
    }

    public final void updateGameStatus() {
        System.out.println(guess);
        if (guess != null) {
            System.out.println("this is letter" + guess);
            System.out.println("game status is updated and size of letter is " + guess.length());
            boolean isCorrect = true;
            boolean isFalse = true;

            for (int i = 0; i < message.getMessageLength(); i++) {
                for (int j = 0; j < message.getCensoredMessageLength(); j++) {
                    if (message.getMessage().charAt(i) == guess.charAt(0)) {
                        if (isCorrect) { //use a string to double check if char was used previously
                            numOfGuesses++;
                            isCorrect = false; //to stop from inner loop increasing numOfGuesses for multiple occurences
                            isFalse = false;

                        }
                        if (j == i * 2) {
                            message.updateCensoredMessage(guess.charAt(0), j);
                        }
                    }
                }
            }
            if (isFalse) {
                numOfIncorrectGuesses++;
            }
        }
    }


    public String gameStatus() {

        String censoredMessage = message.getCensoredMessage().replaceAll("\\s", "");

        if (numOfIncorrectGuesses > 7)
            return "Lost"; //game is lost

        if (censoredMessage.equals(message.getMessage()))
            return "Won"; // game is won

        return "Active"; //game is ongoing
    }
}
