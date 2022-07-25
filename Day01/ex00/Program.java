package ex00;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "John", 100);
        System.out.println("User " + user1.getName() +  ", id " + user1.getIdentifier() +
                " with balance " + user1.getBalance() + " was added");
        User user2 = new User(2, "Jack", 200);
        System.out.println("User " + user2.getName() +  ", id " + user2.getIdentifier() +
                " with balance " + user2.getBalance() + " was added");
        User user3 = new User(3, "Bill", -100);
        System.out.println("User " + user3.getName() +  ", id " + user3.getIdentifier() +
                " with balance " + user3.getBalance() + " was added");
        user1.setBalance(-200);


        UUID uuid1 = UUID.randomUUID();
        Transaction transaction1 = new Transaction(uuid1, user1, user2, Category.CREDIT, 200);
        System.out.println("Transaction " + transaction1.getIdentifier() + " from " +
                transaction1.getSender().getName() + " to " + transaction1.getRecipient().getName() + " was done");
        UUID uuid2 = UUID.randomUUID();
        Transaction transaction2 = new Transaction(uuid1, user1, user2, Category.CREDIT, -100);
    }
}
