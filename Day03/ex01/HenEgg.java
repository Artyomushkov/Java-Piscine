package ex01;

public class HenEgg {
    private int count;
    private boolean ifTimeForEgg;

    HenEgg(int count) {
        this.count = count;
        ifTimeForEgg = true;
    }

    void egg() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < count; i++) {
                if (!ifTimeForEgg)
                    wait();
                System.out.println("Egg");
                ifTimeForEgg = false;
                notify();
            }
        }
    }

    void hen() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < count; i++) {
                if (ifTimeForEgg)
                    wait();
                System.out.println("Hen");
                ifTimeForEgg = true;
                notify();
            }
        }
    }
}
