package ex04;

import java.util.UUID;

enum Category {
    DEBIT, CREDIT
}

public class Transaction {

    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private int transferAmount;

    Transaction(UUID identifier, User recipient, User sender, Category transferCategory, int transferAmount) {
        this.identifier = identifier;
        this.recipient = recipient;
        this.sender = sender;
        this.transferCategory = transferCategory;
        this.transferAmount = transferAmount;
        if (this.transferCategory == Category.DEBIT) {
            if (transferAmount > 0) {
                System.out.println("Impossible to set positive transfer amount for debit transaction " + identifier);
                this.transferAmount = 0;
            }
        }
        if (this.transferCategory == Category.CREDIT) {
            if (transferAmount < 0) {
                System.out.println("Impossible to set negative transfer amount for credit transaction " + identifier);
                this.transferAmount = 0;
            }
        }
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(Category transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setTransferAmount(int transferAmount) {
        if (this.transferCategory == Category.DEBIT) {
            if (transferAmount > 0) {
                System.out.println("Impossible to set positive transfer amount for debit transaction " + identifier);
                return ;
            }
        }
        if (this.transferCategory == Category.CREDIT) {
            if (transferAmount < 0) {
                System.out.println("Impossible to set negative transfer amount for credit transaction " + identifier);
                return ;
            }
        }
        this.transferAmount = transferAmount;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getTransferCategory() {
        return transferCategory;
    }

    public int getTransferAmount() {
        return transferAmount;
    }
}
