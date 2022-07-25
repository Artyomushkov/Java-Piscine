package ex01;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SimilarityAnalyzer {
    private double result;
    private List<Integer> listFileOne;
    private List<Integer> listFileTwo;

    private List<Integer> formListFile(Set<String> uniqueWords,
                                       List<String> wordsFile) {
        List<Integer> res = new ArrayList<>();
        for (String uniqueWord : uniqueWords) {
            int n = 0;
            for (int j = 0; j < wordsFile.size(); j++) {
                if (uniqueWord.equals(wordsFile.get(j))) {
                    n++;
                }
            }
            res.add(n);
        }
        return res;
    }

    public SimilarityAnalyzer(Set<String> uniqueWords, List<String> wordsFileOne,
                              List<String> wordsFileTwo) {
        listFileOne = formListFile(uniqueWords, wordsFileOne);
        listFileTwo = formListFile(uniqueWords, wordsFileTwo);
    }

    private int sumOfSquares(List<Integer> list) {
        int res = 0;
        for (int num : list) {
            res += num * num;
        }
        return res;
    }

    public double calculateSimilarity() {
        int numerator = 0;
        for (int i = 0; i < listFileOne.size(); i++) {
            numerator += listFileOne.get(i) * listFileTwo.get(i);
        }
        double denominator = Math.sqrt(sumOfSquares(listFileOne))
                * Math.sqrt(sumOfSquares(listFileTwo));
        return (numerator / denominator);

    }
}
