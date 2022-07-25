package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileParser {

    private List<String> wordsList;

    public FileParser(String filename) throws IOException {
        wordsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] splitSpace = line.split(" ");
            wordsList.addAll(Arrays.asList(splitSpace));
        }
        reader.close();
    }

    public List<String> getWordsList() {
        return wordsList;
    }
}
