package ex02;

import java.util.Scanner;

public class Program {

    private static int sumOfDigits(int num) {
        int res = 0;
        for (int i = 0; i < 6; ++i) {
            int buf = num / 10;
            int digit = num - buf * 10;
            res += digit;
            num = buf;
        }
        return res;
    }

    private static boolean isPrime(int num) {
        int i = 2;
        boolean isPrime = true;
        while (i < num / 2) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
            i++;
        }
        return isPrime;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        int primeSums = 0;
        while (num != 42) {
            num = scanner.nextInt();
            if (isPrime(sumOfDigits(num))) {
                primeSums++;
            }
        }
        System.out.println("Count of coffee-request - " + primeSums);
        scanner.close();
    }
}