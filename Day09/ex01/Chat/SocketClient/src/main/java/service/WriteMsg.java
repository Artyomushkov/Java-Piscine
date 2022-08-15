package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class WriteMsg extends Thread {

    private BufferedReader inputUser;
    private BufferedWriter out;

    public WriteMsg(BufferedReader inputUser, BufferedWriter out) {
        this.inputUser = inputUser;
        this.out = out;
        start();
    }

    @Override
    public void run() {
        while (true) {
            String userWord;
            try {
                userWord = inputUser.readLine();
                if (userWord.equals("stop")) {
                    out.write("stop" + "\n");
                    break;
                } else {
                    out.write(userWord + "\n");
                }
                out.flush();
            } catch (IOException e) {}
        }
    }
}
