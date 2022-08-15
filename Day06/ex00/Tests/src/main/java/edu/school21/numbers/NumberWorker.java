package edu.school21.numbers;

public class NumberWorker {

    public class IllegalNumberException extends RuntimeException {}

    public boolean isPrime(int number) {
        if (number < 2)
            throw new IllegalNumberException();
        int i = 2;
        boolean isPrime = true;
        while (i < number / 2) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
            i++;
        }
        return isPrime;
    }

    public int digitsSum(int number) {
        if (number < 0)
            number = -number;
        int res = 0;
        while (number != 0) {
            int buf = number / 10;
            int digit = number - buf * 10;
            res += digit;
            number = buf;
        }
        return res;
    }
}
