package ex03;

import java.util.Scanner;

public class Program {

    private static long power10(int power) {
        int res = 1;
        for (int i = 0; i < power; i++) {
            res *= 10;
        }
        return res;
    }

    private static void printResults(long testMins) {
        int week = 1;
        while (testMins != 0) {
            System.out.print("Week ");
            System.out.print(week);
            System.out.print(" ");
            int mark = (int )testMins % 10;
            testMins /= 10;
            for (int i = 0; i < mark; i++) {
                System.out.print("=");
            }
            System.out.print(">\n");
            week++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long testMins = 0;
        int i = 0;
        while (i < 18) {
            String str = scanner.nextLine();
            if (str.equals("42")) {
                break;
            }
            if (!str.equals("Week " + (i + 1))) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            int min = 9;
            for (int j = 0; j < 5; j++) {
                int mark = scanner.nextInt();
                if (mark == 42) {
                    System.err.println("IllegalArgument");
                    System.exit(-1);
                }
                if (mark < min) {
                    min = mark;
                }
            }
            testMins += min * power10(i);
            i++;
            str = scanner.nextLine();
        }
        printResults(testMins);
        scanner.close();
    }
}