package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 100);
        System.out.println("User " + user1.getName() +  ", id " + user1.getIdentifier() +
                " with balance " + user1.getBalance() + " was added");
        User user2 = new User("Jack", 200);
        System.out.println("User " + user2.getName() +  ", id " + user2.getIdentifier() +
                " with balance " + user2.getBalance() + " was added");
        User user3 = new User("Bill", -100);
        System.out.println("User " + user3.getName() +  ", id " + user3.getIdentifier() +
                " with balance " + user3.getBalance() + " was added");
    }
}
