package ca.cmpt213.a4.onlinehangman.model;

import java.io.BufferedReader;
import java.io.File;
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
            System.out.println(new File("commonWords.txt").getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/Oliver/IdeaProjects/cmpt213as4/src/commonWords.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words.add(line);
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", "commonWords.txt");
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
