package school21.sockets.app;

import school21.sockets.server.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length < 1) {
            throw new RuntimeException("There is no argument!");
        }
        if (!args[0].startsWith("--port="))
            throw new RuntimeException("Error in argument");
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        Server.start(port);
    }
}
