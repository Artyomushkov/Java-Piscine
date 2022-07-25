package ex00;

public class Program {
    public static void main(String[] args) throws InterruptedException {

        if (args.length < 1)
            throw new RuntimeException("ex00.Program can't start with no arguments");
        if (!args[0].startsWith("--count="))
            throw new RuntimeException("Wrong argument");
        int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        Thread threadHen = new Thread(new Runner(count,"Hen"));
        Thread threadEgg = new Thread(new Runner(count, "Egg"));
        threadHen.start();
        threadEgg.start();
        threadHen.join();
        threadEgg.join();
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}

class Runner implements Runnable {

    private int count;
    private String word;

    Runner(int count, String word) {
        this.count = count;
        this.word = word;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println(word);
        }
    }
}