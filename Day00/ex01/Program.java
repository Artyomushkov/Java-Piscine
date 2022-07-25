package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        if (num < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        int i = 2;
        boolean isPrime = true;
        while (i < num / 2) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
            i++;
        }
        System.out.println(isPrime + " " + (i - 1));
        scanner.close();
    }
}
