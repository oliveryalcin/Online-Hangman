package ca.cmpt213.a4.onlinehangman.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads in the words used to play the game from the commonWords.txt file
 */
public class WordManager {
    private static WordManager single_instance;
    private static List<String> words;

    private WordManager() {
        //read words into string
        words = new ArrayList<>();
        try {
            String file ="src/commonWords.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", "src/commonWords.txt");
            e.printStackTrace();
        }

    }

    public static WordManager getSingleInstance() {
        if (single_instance == null) {
            single_instance = new WordManager();
        }

        return single_instance;
    }

    public static int getNumOfWords() {
        return words.size();
    }

    public static String getWordAt(int index) {
        return words.get(index);
    }

}
