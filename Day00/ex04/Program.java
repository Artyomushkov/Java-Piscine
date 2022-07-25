package ex04;

import java.util.Scanner;

public class Program {
    private static void printResults(int[][] resArr) {
        for (int j = 0; j < 11; j++) {
            for (int i = 0; i < 10; i++) {
                if (resArr[i][1] != 0) {
                    if (resArr[i][2] == 10 - j) {
                        System.out.print(resArr[i][1]);
                        if (resArr[i][1] >= 100)
                            System.out.print(" ");
                        else if (resArr[i][1] >= 10)
                            System.out.print("  ");
                        else
                            System.out.print("   ");
                    } else if (resArr[i][2] > 10 - j) {
                        System.out.print("#   ");
                    }
                }
            }
            System.out.println();
        }
        for (int i = 0; i < 10; i++) {
            if (resArr[i][1] != 0)
                System.out.print((char)resArr[i][0] + "   ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        char[] charArr = str.toCharArray();
        int[] numOfLetters = new int[65536];
        for (char letter : charArr) {
            numOfLetters[letter]++;
        }
        int[][] resArr = new int[10][3];
        for (int i = 0; i < 10; ++i) {
            int maxIndex = 0;
            int maxNum = 0;
            int j = 0;
            while (j < numOfLetters.length) {
                if (numOfLetters[j] > maxNum) {
                    maxNum = numOfLetters[j];
                    maxIndex = j;
                }
                j++;
            }
            resArr[i][0] = maxIndex;
            resArr[i][1] = maxNum;
            numOfLetters[maxIndex] = -1;
        }
        for (int i = 0; i < 10; i++) {
            if (resArr[0][1] != 0)
                resArr[i][2] = resArr[i][1] * 10 / resArr[0][1];
        }
        printResults(resArr);
        scanner.close();
    }
}
