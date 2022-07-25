package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Room {
    private long id;
    private String name;
    private User admin;
    private List<Message> messageList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && Objects.equals(name, room.name) && Objects.equals(admin, room.admin) && Objects.equals(messageList, room.messageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, admin, messageList);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", admin=" + admin +
                ", messageList=" + messageList +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getAdmin() {
        return admin;
    }

    public List<Message> getMessageList() {
        return messageList;
    }
}
