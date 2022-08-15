package school21.sockets.models;

public class Message {
    private String login;
    private String message;

    public Message(String login, String message) {
        this.login = login;
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }
}
