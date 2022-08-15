package school21.sockets.server;

import school21.sockets.models.Message;
import school21.sockets.services.UsersService;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ForEachClient extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    private String name;
    private boolean ifRegistered = false;

    private UsersService usersService;

    public ForEachClient(Socket socket, UsersService usersService) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.usersService = usersService;
        start();
    }

    private void registration() throws IOException, SQLException {
        out.write("Enter username: " + '\n');
        out.flush();
        String login = in.readLine();
        name = login;
        out.write("Enter password: " + '\n');
        out.flush();
        String password = in.readLine();
        usersService.addToDatabase(login, password);
        ifRegistered = true;
    }

    private void authorisation() throws IOException {
        out.write("Enter username: " + '\n');
        out.flush();
        String login = in.readLine();
        out.write("Enter password: " + '\n');
        out.flush();
        String password = in.readLine();
        boolean ifRegistered = usersService.ifRegisteredToDatabase(login, password);
        if (ifRegistered) {
            name = login;
            ifRegistered = true;
        }
        else {
            System.out.println("Authorisation failed!");
            ifRegistered = false;
        }
    }

    private void messaging() throws IOException, SQLException {
        if (!ifRegistered) {
            out.write("User not authorized + '\n");
            out.flush();
        }
        while (true) {
            String message = in.readLine();
            if (message.equals("Exit")) {
                break;
            }
            for (ForEachClient vr : Server.getClientList()) {
                vr.send(name + ": " + message);
            }
            usersService.addMessageToDb(new Message(name, message));
        }
    }

    @Override
    public void run() {
        try {
            out.write("Hello from server!" + '\n');
            out.flush();
            while (true) {
                String word = in.readLine();
                switch (word) {
                    case "signUp" :
                        registration();
                        break;
                    case "signIn" :
                        authorisation();
                        break;
                    case "Start messaging":
                        messaging();
                        break;
                    case "Exit" :
                        socket.close();
                        break;
                    default:
                        out.write("No such command!" + '\n');
                        out.flush();
                        break;
                }
            }
        } catch (IOException e) {} catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(String message) {
        try {
            out.write(message + '\n');
            out.flush();
        } catch (IOException e) {}
    }
}
