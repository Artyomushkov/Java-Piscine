package ex01;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1)
            throw new RuntimeException("ex00.Program can't start with no arguments");
        if (!args[0].startsWith("--count="))
            throw new RuntimeException("Wrong argument");
        int count = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        HenEgg printer = new HenEgg(count);
        Thread threadEgg = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.egg();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread threadHen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.hen();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadEgg.start();
        threadHen.start();
    }
}
