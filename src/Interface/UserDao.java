package Interface;
import java.util.List;
import entity.User;

public interface UserDao {
    void addUser(User user);
    List<User> getAllUsers();
    User getUserByName(String name);
    User findUserByName(String name);
}