package epam.task5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserAccountStorage {

    private static final String ACCOUNTS_DIR = "accounts";

    public static void saveAccount(UserAccount account) throws IOException {
        Path accountsPath = Paths.get(ACCOUNTS_DIR);
        Files.createDirectories(accountsPath);
        String accountFilename = "account_" + account.getUserId() + ".dat";
        FileOutputStream fos = new FileOutputStream(ACCOUNTS_DIR + File.separator + accountFilename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(account);
        oos.close();
        fos.close();
    }

    public static UserAccount loadAccount(int userId) throws FileNotFoundException, IOException, ClassNotFoundException {
        String accountFilename = "account_" + userId + ".dat";
        FileInputStream fis = new FileInputStream(ACCOUNTS_DIR + File.separator + accountFilename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        UserAccount account = (UserAccount) ois.readObject();
        ois.close();
        fis.close();
        return account;
    }
}
