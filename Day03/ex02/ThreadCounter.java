package ex02;

import java.util.List;

public class ThreadCounter implements Runnable {
    private List<Integer> list;
    private int begin;
    private int end;
    private int sum;
    private int index;

    public ThreadCounter(List<Integer> list, int begin, int end, int index) {
        this.list = list;
        this.begin = begin;
        this.end = end;
        this.sum = 0;
        if (this.end > list.size()) {
            this.end = list.size();
        }
        this.index = index;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            sum += list.get(i);
        }
        System.out.println("Thread " + index + ": from " + begin + " to " + (end - 1) +
                " sum is " + sum);
    }

    public int getSum() {
        return sum;
    }
}
