package ex02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 2)
            throw new RuntimeException("ex00.Program can't start with no arguments");
        if (!args[0].startsWith("--arraySize=") || !args[1].startsWith("--threadsCount="))
            throw new RuntimeException("Wrong argument");
        int arraySize = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        int threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf('=') + 1));

        Random rand = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            list.add(rand.nextInt(1000));
        }
        int sumUsual = 0;
        for (Integer integer : list) {
            sumUsual += integer;
        }
        System.out.println("Sum: " + sumUsual);

        int sizeThr = arraySize / threadsCount + 1;
        ThreadCounter[] thrCount = new ThreadCounter[threadsCount];
        Thread[] threads = new Thread[threadsCount];
        int begin = 0;
        for (int i = 0; i < threadsCount; i++) {
            thrCount[i] = new ThreadCounter(list, begin, begin + sizeThr, i);
            begin += sizeThr;
            threads[i] = new Thread(thrCount[i]);
            threads[i].start();
        }
        for (int i = 0; i < threadsCount; i++) {
            threads[i].join();
        }
        int sumThreads = 0;
        for (int i = 0; i < threadsCount; i++) {
            sumThreads += thrCount[i].getSum();
        }
        System.out.println("Sum by threads: " + sumThreads);
    }
}
