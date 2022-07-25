package ex04;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsService transService = new TransactionsService();
        User user1 = new User("Jack", 400);
        User user2 = new User("Bob", 10000);
        User user3 = new User("John", 3000);
        transService.addUser(user1);
        transService.addUser(user2);
        transService.addUser(user3);
        transService.transferTransaction(user2.getIdentifier(),
                user3.getIdentifier(), 500);
        System.out.println("Bob balance " + user2.getBalance());
        try {
            transService.transferTransaction(user2.getIdentifier(),
                    user1.getIdentifier(), 1000);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        UUID uuid = UUID.randomUUID();
        System.out.println("ID of transaction that will be added," +
                " but with not valid method: " + uuid);
        Transaction notValTransaction = new Transaction(uuid, user1, user2, Category.CREDIT, 100);
        Transaction notValTransaction2 = new Transaction(UUID.randomUUID(), user1, user2, Category.CREDIT, 100);
        user1.getTransactions().addTransaction(notValTransaction);
        user2.getTransactions().addTransaction(notValTransaction2);
        Transaction[] notValArr = transService.getNotValidTransactions();
        for (int i = 0; i < notValArr.length; i++) {
            System.out.println("Not valid: " + notValArr[i].getIdentifier());
        }
    }
}