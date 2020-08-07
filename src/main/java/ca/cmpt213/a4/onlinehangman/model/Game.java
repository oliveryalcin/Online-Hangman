package ca.cmpt213.a4.onlinehangman.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Model class where in which an individual and unique is Game instance is created and is the class where in which
 * the gameplay logic is implemented to facilitate gameplay.
 *
 * Student id: 301350814
 * Email: owells@sfu.ca
 */
public class Game implements Serializable {

    private String guess;
    private StringBuilder guessHistory;
    private long id;
    private Word word;
    private int numOfGuesses;
    private int numOfCorrectGuesses; //will use to keep track of game progress or maybe i can just compare strings idk
    private int numOfIncorrectGuesses;
    private final WordReader wordReader = WordReader.getSingleInstance();
    private String gameStatus;


    public Game(long id) { //dependency injection of the word

        this.id = id;
        this.numOfIncorrectGuesses = 0;
        this.numOfCorrectGuesses = 0;
        this.numOfGuesses = 0;
        this.guessHistory = new StringBuilder();
        this.gameStatus = "Active";
        initializeGameWord(); //
    }

    public Game() { //dont want to initialize game word here as it is redundant because this Game is a throwaway template
        this.id = 0;
        this.numOfIncorrectGuesses = 0;
        this.numOfCorrectGuesses = 0;
        this.numOfGuesses = 0;
        this.guessHistory = new StringBuilder();
        this.gameStatus = "Active";
    }

    public StringBuilder getGuessHistory() {
        return guessHistory;
    }

    public void setGuessHistory(StringBuilder guessHistory) {
        this.guessHistory = guessHistory;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public void setNumOfCorrectGuesses(int numOfCorrectGuesses) {
        this.numOfCorrectGuesses = numOfCorrectGuesses;
    }

    private void initializeGameWord() { //helper method
        Random random = new Random();
        int myRand = random.nextInt(WordReader.getNumOfWords());
        word = new Word((WordReader.getWordAt(myRand)));
        System.out.println(word.getWord());
        System.out.println(word.getCensoredWord());
    }

    public Word getWord() {
        return word;
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
        if (guess != null && (gameStatus().equals("Active"))) {
            System.out.println("this is letter" + guess);
            System.out.println("game status is updated and size of letter is " + guess.length());
            boolean isCorrect = true;
            boolean isFalse = true;

            for (int h = 0; h < guessHistory.length(); h++) { //sole purpose is to make the algorithm smoother and check for duplicates
                if (guess.charAt(0) == guessHistory.toString().charAt(h)) {
                    numOfGuesses++;
                    return; //nothing to update, dont append identical letters will affect performance badly if I did so.
                }
            }
            for (int i = 0; i < word.getWordLength(); i++) {
                for (int j = 0; j < word.getCensoredWordLength(); j++) {
                    if (word.getWord().charAt(i) == guess.charAt(0)) {
                        for (int k = 0; k < guessHistory.length(); k++) {
                            if (guessHistory.toString().charAt(k) == guess.charAt(0)) {
                                isCorrect = false;
                                isFalse = false;
                                break;
                            }
                        }
                        if (isCorrect) { //use a string to double check if char was used previously
                            numOfGuesses++;
                            numOfCorrectGuesses++;
                            isCorrect = false; //to stop from inner loop increasing numOfGuesses for multiple occurences
                            isFalse = false;
                            guessHistory.append(guess);
                        }
                        if (j == i * 2) {
                            word.updateCensoredWord(guess.charAt(0), j);
                        }
                    }
                }
            }
            if (isFalse) {
                numOfIncorrectGuesses++;
                numOfGuesses++;
            }
        }
    }


    public String gameStatus() {

        String censoredMessage = word.getCensoredWord().replaceAll("\\s", "");

        if (numOfIncorrectGuesses > 7) {
            gameStatus = "Lost";
            return gameStatus; //game is lost
        }

        else if (censoredMessage.equals(word.getWord())) {
            gameStatus = "Won";
            return gameStatus; // game is won
        }
        else if (!(gameStatus.equals("Lost") || gameStatus.equals("Won"))) { //this stops cheating
            gameStatus = "Active";
        }

        return gameStatus; //game is ongoing
    }

}
