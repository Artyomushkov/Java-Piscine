package ex00;

public class User {

    private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name, Integer balance) {
        this.identifier = identifier;
        this.name = name;
        this.balance = balance;
        if (balance < 0) {
            System.out.println("Unable to make user " + name + " with negative balance," +
                    " it will be set to zero!");
            this.balance = 0;
        }
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if (balance < 0) {
            System.out.println("Unable to set negative balance for user " + name);
            return;
        }
        this.balance = balance;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }
}