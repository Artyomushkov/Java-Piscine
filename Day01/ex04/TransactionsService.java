package ex04;

import java.util.UUID;

class IllegalTransactionException extends RuntimeException {
    IllegalTransactionException(String message) {
        super(message);
    }
}

public class TransactionsService {

    private UsersList usersList;

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public int getUserBalance(User user) {
        return user.getBalance();
    }

    public void transferTransaction(Integer recipientId, Integer senderId, int transferAmount) {
        if (transferAmount < 0)
            throw new RuntimeException("Transfer amount can't be negative!");
        User recipient = usersList.getUserById(recipientId);
        User sender = usersList.getUserById(senderId);
        if (sender.getBalance() < transferAmount) {
            throw new IllegalTransactionException("User " + sender.getName() + " don't have enough money!");
        }
        UUID uuid = UUID.randomUUID();
        Transaction transaction1 = new Transaction(uuid, recipient, sender, Category.DEBIT, -transferAmount);
        Transaction transaction2 = new Transaction(uuid, sender, recipient, Category.CREDIT, transferAmount);
        sender.getTransactions().addTransaction(transaction1);
        recipient.getTransactions().addTransaction(transaction2);
        recipient.setBalance(recipient.getBalance() + transferAmount);
        sender.setBalance(sender.getBalance() - transferAmount);
    }

    public Transaction[] getUserTransactions(User user) {
        return user.getTransactions().toArray();
    }

    public void deleteTransaction(int userId, UUID transactionId) {
        User user = usersList.getUserById(userId);
        user.getTransactions().deleteTransaction(transactionId);
    }

    public Transaction[] getNotValidTransactions() {
        TransactionsList res = new TransactionsLinkedList();
        for (int i = 0; i < usersList.getUsersNumber(); i++) {
            User user1 = usersList.getUserByIndex(i);
            Transaction[] transArr = user1.getTransactions().toArray();
            for (Transaction t : transArr) {
                boolean ifValid = false;
                for (Transaction t2 : t.getSender().getTransactions().toArray()) {
                    if (t != t2) {
                        if (t.getIdentifier() == t2.getIdentifier()) {
                            ifValid = true;
                            break;
                        }
                    }
                }
                for (Transaction t2 : t.getRecipient().getTransactions().toArray()) {
                    if (t != t2) {
                        if (t.getIdentifier() == t2.getIdentifier()) {
                            ifValid = true;
                            break;
                        }
                    }
                }
                if (!ifValid)
                    res.addTransaction(t);
            }
        }
        return res.toArray();
    }






}
