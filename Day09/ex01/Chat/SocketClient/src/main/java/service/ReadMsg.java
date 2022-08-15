package service;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadMsg extends Thread {

    private BufferedReader in;

    public ReadMsg(BufferedReader in) {
        this.in = in;
        start();
    }

    @Override
    public void run() {
        String str;
        try {
            while (true) {
                str = in.readLine();
                System.out.println(str);
                if (str.equals("stop")) {
                    break;
                }
            }
        } catch (IOException e) {}
    }
}
