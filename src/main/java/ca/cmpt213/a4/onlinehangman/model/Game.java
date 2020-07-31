package ca.cmpt213.a4.onlinehangman.model;

import java.util.Random;

public class Game {

    private long id;
    private Message message;
    private int numOfGuesses;
    private int numOfCorrectGuesses; //will use to keep track of game progress or maybe i can just compare strings idk
    private int numOfIncorrectGuesses;
    private final WordManager wordManager = WordManager.getSingleInstance();
    private String letter;

    public Game(long id) { //dependency injection of the word
        this.id = id;
        this.numOfIncorrectGuesses = 0;
        this.numOfCorrectGuesses = 0;
        this.numOfGuesses = 0;
        initializeGameWord();
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
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

    public void updateGameStatus() {
        System.out.println(letter);
        if (letter != null) {

                System.out.println("this is letter" + letter);
                System.out.println("game status is updated and size of letter is "+ letter.length());
                if (this.message != null) {
                    boolean isCorrect = false;
                    for (int i = 0; i < message.getMessageLength(); i++) {
                        if (this.message.getMessage().charAt(i) == letter.charAt(0)) {
                            if (!isCorrect) {
                                numOfCorrectGuesses++; //only want this to execute once if letter is duplicate
                            }

                            if (i != 0)
                                this.message.updateCensoredMessage(letter.charAt(0), i - 1);
                            else {
                                this.message.updateCensoredMessage(letter.charAt(0), i);
                            }
                            isCorrect = true;
                        }
                    }
                    if (!isCorrect) { //if letter does not exist and answer is incorrect then this will execute
                        numOfIncorrectGuesses++;
                    }
                    numOfGuesses++;
                }
            }
        }

    public String gameStatus() {
        if (numOfIncorrectGuesses > 6)
            return "Lost"; //game is lost

        if (numOfIncorrectGuesses < 6 && numOfCorrectGuesses == message.getMessage().length())
            return "Won"; // game is won

        return "Active"; //game is ongoing
    }
}
