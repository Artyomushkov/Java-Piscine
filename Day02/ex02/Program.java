package ex02;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IOException("Directory argument needed!");
        }
        String folder = args[0].substring(args[0].indexOf('=') + 1);
        Path nowDir = Paths.get(folder);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            String[] comm = str.split(" ");
            if (comm[0].equals("exit")) {
                break;
            }
            else if (comm[0].equals("ls")) {
                try {
                    Command.ls(nowDir);
                }
                catch (IOException e) {
                    System.out.println("ls error");
                }
            }
            else if (comm[0].equals("cd")) {
                try {
                    nowDir = Command.cd(comm, nowDir);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (comm[0].equals("mv")) {
                try {
                    Command.mv(comm, nowDir);
                }
                catch (Exception e) {
                    System.out.println("mv error: " + e.getMessage());
                }
            }
            else
                System.out.println("No such command!");
        }
        sc.close();
    }
}
