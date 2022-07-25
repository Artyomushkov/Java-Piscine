package ex04;

import java.util.UUID;

class TransactionNotFoundException extends RuntimeException {
    TransactionNotFoundException(String message) {
        super(message);
    }
}

public class TransactionsLinkedList implements TransactionsList {

    private static class Node {

        private Node prev;
        private Transaction transaction;
        private Node next;

        Node(Transaction transaction, Node next, Node prev) {
            this.transaction = transaction;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public TransactionsLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        Node tmp = new Node(transaction, head, null);
        if (head != null) {
            head.prev = tmp;
        }
        head = tmp;
        size++;
    }

    @Override
    public void deleteTransaction(UUID id) {
        Node del = head;
        boolean ifFound = false;
        while (del != null) {
            if (del.transaction.getIdentifier() == id) {
                ifFound = true;
                break;
            }
            del = del.next;
        }
        if (!ifFound) {
            throw new TransactionNotFoundException("Transaction " + id + " not found!");
        }
        if (head == del) {
            head = del.next;
        }
        if (del.next != null) {
            del.next.prev = del.prev;
        }
        if (del.prev != null) {
            del.prev.next = del.next;
        }
        size--;
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] res = new Transaction[size];
        Node tmp = head;
        for (int i = 0; i < size; i++) {
            res[i] = tmp.transaction;
            tmp = tmp.next;
        }
        return res;
    }
}