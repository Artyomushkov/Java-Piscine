package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private long id;
    private String login;
    private String password;
    private List<Room> roomsAdmin;
    private List<Room> roomsUser;


    public User(long id, String login, String password, List<Room> rooomsAdmin, List<Room> roomsUser) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roomsAdmin = rooomsAdmin;
        this.roomsUser = roomsUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(roomsAdmin, user.roomsAdmin) && Objects.equals(roomsUser, user.roomsUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, roomsAdmin, roomsUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roomsAdmin=" + roomsAdmin +
                ", roomsUser=" + roomsUser +
                '}';
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Room> getRoomsAdmin() {
        return roomsAdmin;
    }

    public List<Room> getRoomsUser() {
        return roomsUser;
    }
}
