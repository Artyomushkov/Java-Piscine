package app;

import service.Client;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("There is no argument!");
        }
        if (!args[0].startsWith("--server-port=")) {
            throw new RuntimeException("Error in argument");
        }
        int port = Integer.parseInt(args[0].substring(args[0].indexOf('=') + 1));
        Client.start(port);
    }
}
