package ex01;

public class User {

    private Integer identifier;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
        if (balance < 0) {
            System.out.println("Unable to make user " + name + " with negative balance," +
                    " it will be set to zero!");
            this.balance = 0;
        }
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