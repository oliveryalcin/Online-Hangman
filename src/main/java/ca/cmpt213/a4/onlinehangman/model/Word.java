package ca.cmpt213.a4.onlinehangman.model;

/**
 * Word class used to update, store and initialize the word that is being guessed and the censored version of the word
 * used in the Game.
 * Student id: 301350814
 * Email: owells@sfu.ca
 */
public class Word {
    private String word;
    private char[] censoredWord;

    public Word() {
        this.word = "";
        this.censoredWord = new char[0];
    }

    public Word(String s) {
        setWord(s);
    }

    public String getWord() {
        return word;
    }

    public int getWordLength() {
        return word.length();
    }

    public int getCensoredWordLength() {
        return censoredWord.length;
    }

    public void setWord(String message) {
        this.word = message;
        this.censoredWord = new char[(this.word.length() * 2) - 1];
        for (int i = 0; i < (this.word.length() * 2) - 1; i++) {

            if (i % 2 == 0)
                censoredWord[i] = '_';

            if (i % 2 != 0)
                censoredWord[i] = ' ';
        }
    }

    public String getCensoredWord() {
        return String.valueOf(this.censoredWord);
    }

    public void updateCensoredWord(char letter, int index) {

        System.out.println(letter + " is my input");
        this.censoredWord[index] = letter; // e.g rewrite _ with 'a'
    }

}
