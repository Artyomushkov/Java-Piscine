package ex01;

public class UserIdsGenerator {

    private static UserIdsGenerator instance;
    private Integer usersInSystem;

    private UserIdsGenerator() {
        usersInSystem = 0;
    }

    public static UserIdsGenerator getInstance() {
        if (instance == null)
            instance = new UserIdsGenerator();
        return instance;
    }

    public Integer generateId() {
        usersInSystem++;
        return usersInSystem;
    }
}
