package edu.school21.chat.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long id;
    private User sender;
    private Room room;
    private String text;
    private LocalDateTime time;

    public Message(Long id, User user, Room room, String message, LocalDateTime time) {
        this.id = id;
        this.sender = user;
        this.room = room;
        this.text = message;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(sender, message.sender) && Objects.equals(room, message.room) && Objects.equals(text, message.text) && Objects.equals(time, message.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, room, text, time);
    }

    @Override
    public String toString() {
        return "Message {" + "\n " +
                "id=" + id + ",\n " +
                "sender=" + sender + ",\n" +
                "room=" + room + ",\n" +
                "text=" + text + ",\n" +
                "time=" + time +
                '}';
    }

    public long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Room getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

