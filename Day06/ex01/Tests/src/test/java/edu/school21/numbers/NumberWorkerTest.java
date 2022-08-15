package edu.school21.numbers;

import edu.school21.numbers.NumberWorker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    NumberWorker numberWorker;

    @BeforeEach
    public void init() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 11, 19})
    public void isPrimeForPrimes(int num) {
        Assertions.assertTrue(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 48, 33})
    public void isPrimeForNotPrimes(int num) {
        Assertions.assertFalse(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, 0, 1})
    public void isPrimeForIncorrectNumbers(int num) {
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"})
    void checkDigitsSumTrue(int num, int res) {
        Assertions.assertEquals(numberWorker.digitsSum(num), res);
    }

}
