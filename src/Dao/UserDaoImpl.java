package Dao;

import Storage.BaseCsvStorage;
import entity.User;

import java.util.List;

public class UserDaoImpl extends BaseCsvStorage<User> implements UserDao {

    private static final String FILE_PATH = "C:\\Users\\moberholzer\\Documents\\GitHub\\Bibliotheksverwaltungssystem\\src\\Storage\\User.csv";

    public UserDaoImpl() {
        super(FILE_PATH, User.class);
        addColumn("userId", int.class);
        addColumn("name", String.class);
        addColumn("address", String.class);
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
    public int addUser(String name, String address) {
        
    	int maxId = 0;
        for (User user : getAllValues()) {
            if (user.getUserId() > maxId) {
                maxId = user.getUserId();
            }
        }
        var newCustomerNumber = maxId + 1;
    	var newUser = new User(newCustomerNumber,name, address);
    	addValue(newUser);
    	return newCustomerNumber;
    }

    @Override
    public List<User> getAllUsers() {
        return getAllValues();
    }

    @Override
    public User getUserByUserId(int userId) {
        return getAllValues().stream()
                .filter(user -> user.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findUsersByName(String name) {
        return getAllValues().stream()
                .filter(user -> user.getName().equalsIgnoreCase(name)).toList();
    }
    
    @Override
    public void DeletUserByUser(User userToDelete) {
        for (var user : getAllUsers()) 
            if (user.getUserId() == userToDelete.getUserId()) {
            	values.remove(user);
            }
    }
}
