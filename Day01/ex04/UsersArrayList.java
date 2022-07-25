package ex04;

public class UsersArrayList implements UsersList {

    private User[] arr;
    int size;
    int cap;

    public UsersArrayList() {
        cap = 10;
        size = 0;
        arr = new User[cap];
    }

    private static class UserNotFoundException extends RuntimeException {
       UserNotFoundException(String errorMessage) {
           super(errorMessage);
       }
    }

    @Override
    public void addUser(User user) {
        if (size == cap) {
            cap = cap + cap / 2;
            User[] newArr = new User[cap];
            for (int i = 0; i < size; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[size] = user;
        size++;
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < size; i++) {
            if (id == arr[i].getIdentifier()) {
                return arr[i];
            }
        }
        throw new UserNotFoundException("User not found in array!");
    }

    @Override
    public User getUserByIndex(int index) {
        return arr[index];
    }

    @Override
    public int getUsersNumber() {
        return size;
    }
}