package ex02;

public class Program {
    public static void main(String[] args) {
        UsersList list = new UsersArrayList();
        for (int i = 0; i < 6; i++) {
            User user = new User("Jack", 200);
            list.addUser(user);
        }
        for (int i = 0; i < 6; i++) {
            User user = new User("John", 400);
            list.addUser(user);
        }
        System.out.println("Size: " + list.getUsersNumber());
        System.out.println("Name of 7th: " + list.getUserByIndex(7).getName());
        System.out.println("Name with 5th id: " + list.getUserById(5).getName());
        try {
            System.out.println(list.getUserById(13));
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
