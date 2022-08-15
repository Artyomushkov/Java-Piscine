package school21.sockets.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.sockets.config.SocketsApplicationConfig;
import school21.sockets.services.UsersService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
    private static List<ForEachClient> clientList = Collections.
            synchronizedList(new ArrayList<>());

    public static void start(int port) throws IOException {
        ServerSocket server = new ServerSocket(port);
        ApplicationContext context = new AnnotationConfigApplicationContext
                (SocketsApplicationConfig.class);
        UsersService usersService = context.getBean(UsersService.class);
        try {
            while (true) {
                Socket socket = server.accept();
                try {
                    clientList.add(new ForEachClient(socket, usersService));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }

    public static List<ForEachClient> getClientList() {
        return clientList;
    }
}
