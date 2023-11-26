package epam.task5;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserDao {
    public void saveAccount(UserAccount account) throws IOException {
        UserAccountStorage.saveAccount(account);
    }

    public UserAccount loadAccount(int userId) throws FileNotFoundException, IOException, ClassNotFoundException {
        return UserAccountStorage.loadAccount(userId);
    }
}
