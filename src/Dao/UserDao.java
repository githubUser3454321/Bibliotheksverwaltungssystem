package Dao;
import java.util.List;

import entity.User;

public interface UserDao {
	void addUser(User user);
	List<User> getAllUsers();
    User getUserByUserId(int userId);
    List<User> findUsersByName(String name);
}