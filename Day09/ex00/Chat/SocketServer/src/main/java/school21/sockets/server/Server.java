package school21.sockets.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.sockets.config.SocketsApplicationConfig;
import school21.sockets.services.UsersService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;


    private static void workWithDatabase(String login, String password) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        UsersService usersService = context.getBean(UsersService.class);
        usersService.initDatabase();
        usersService.addToDatabase(login, password);
    }
    public static void start(int port) {
        try {
            try {
                server = new ServerSocket(port);
                clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    out.write("Hello from server!" + '\n');
                    out.flush();
                    String command = in.readLine();
                    if (!command.equals("signUp")) {
                        throw new RuntimeException("Wrong command!");
                    }
                    out.write("Enter username: " + '\n');
                    out.flush();
                    String login = in.readLine();
                    out.write("Enter password: " + '\n');
                    out.flush();
                    String password = in.readLine();
                    workWithDatabase(login, password);
                    out.write("Successful!" + '\n');
                    out.flush();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                    }
                } finally {
                    server.close();
                }
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
