package service;

import java.io.*;
import java.net.Socket;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void start(int port) {
        try {
            try {
                clientSocket = new Socket("localhost", port);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String hello = in.readLine();
                System.out.println(hello);
                ReadMsg readMsg = new ReadMsg(in);
                while (true) {
                    String command = reader.readLine();
                    out.write(command + '\n');
                    out.flush();
                }
            } finally {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*public static void start(int port) {
        try {
            try {
                clientSocket = new Socket("localhost", port);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String hello = in.readLine();
                System.out.println(hello);
                String command = reader.readLine();
                out.write(command + '\n');
                out.flush();
                System.out.println(in.readLine());
                String login = reader.readLine();
                out.write(login + '\n');
                out.flush();
                System.out.println(in.readLine());
                String password = reader.readLine();
                out.write(password + '\n');
                out.flush();
                System.out.println(in.readLine());
            } finally {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
