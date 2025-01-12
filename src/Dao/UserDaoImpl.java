package Dao;

import entity.User;
import Interface.UserDao;
import BaseStorage.BaseCsvStorage;
import java.util.List;

public class UserDaoImpl extends BaseCsvStorage<User> implements UserDao {

    private static final String FILE_PATH = "C:\\Users\\moberholzer\\eclipse-workspace\\Bmi\\src\\Storage\\User.csv";

    public UserDaoImpl() {
        super(FILE_PATH, User.class);
        // Define columns for the User class: name (String), age (int), height (double), weight (double)
        addColumn("name", String.class);
        addColumn("age", int.class);
        addColumn("height", double.class);
        addColumn("weight", double.class);
        initializeColumns();
    }

    @Override
    protected User parseLine(String line) {
        return parseLineWithDynamicColumns(line);
    }

    @Override
    protected String formatObjectT(User user) {
        return super.formatObject(user);
    }

    @Override
    public void addUser(User user) {
        addValue(user);
    }

    @Override
    public List<User> getAllUsers() {
        return getAllValues();
    }

    @Override
    public User getUserByName(String name) {
        return getAllValues().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findUserByName(String name) {
        return getAllValues().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
