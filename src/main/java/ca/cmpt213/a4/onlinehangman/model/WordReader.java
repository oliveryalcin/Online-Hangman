package ca.cmpt213.a4.onlinehangman.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class which reads in the words from the commonWords.txt file and aids in initializing Games.
 * Student id: 301350814
 * Email: owells@sfu.ca
 */
public class WordReader {
    private static WordReader single_instance = null;
    private static List<String> words;

    private WordReader() {
        //read words into string
        words = new ArrayList<>();
        try {
            String file = "src/commonWords.txt";
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

    public static WordReader getSingleInstance() {
        if (single_instance == null) {
            single_instance = new WordReader();
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
