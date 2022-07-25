package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        TransactionsList list = new TransactionsLinkedList();
        User user1 = new User("Jack", 400);
        User user2 = new User("John", 300);
        Transaction transaction1 = new Transaction(UUID.randomUUID(), user1, user2, Category.CREDIT, 200);
        UUID uuid1 = UUID.randomUUID();
        Transaction transaction2 = new Transaction(uuid1, user1, user2, Category.CREDIT, 300);
        list.addTransaction(transaction1);
        list.addTransaction(transaction2);
        user1.setTransactions(list);
        Transaction[] transArr = user1.getTransactions().toArray();
        for (int i = 0; i < transArr.length; i++) {
            System.out.println("IDs in array: " + transArr[i].getIdentifier());
        }

        list.deleteTransaction(uuid1);
        System.out.println();
        System.out.println("After delete:");
        transArr = user1.getTransactions().toArray();
        for (int i = 0; i < transArr.length; i++) {
            System.out.println("ID in array: " + transArr[i].getIdentifier());
        }

        System.out.println();
        try {
            list.deleteTransaction(uuid1);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
