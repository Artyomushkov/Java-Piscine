package ex01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Program {
    public static void main(String[] args) throws IOException {

        final String outFile = "dictionary.txt";

        FileParser file1;
        FileParser file2;
        try {
            file1 = new FileParser(args[0]);
            file2 = new FileParser(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        List<String> wordsFirstFile = file1.getWordsList();
        List<String> wordsSecondFile = file2.getWordsList();
        if (wordsFirstFile.isEmpty() || wordsSecondFile.isEmpty()) {
            System.out.println("One of files is empty!");
            System.exit(0);
        }
        Set<String> uniqueWords = new HashSet<String>(wordsFirstFile);
        uniqueWords.addAll(wordsSecondFile);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (String word : uniqueWords) {
                writer.write(word + " ");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        SimilarityAnalyzer similarityAnalyzer = new SimilarityAnalyzer(uniqueWords,
                wordsFirstFile,wordsSecondFile);
        double res = similarityAnalyzer.calculateSimilarity();
        if ((1 - res) < 0.0001)
            res = 1;
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.DOWN);
        System.out.println("Similarity = " + df.format(res));
    }
}
