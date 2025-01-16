package Dao;
import java.util.List;

import entity.User;

public interface UserDao {
	int addUser(String name, String address );
	List<User> getAllUsers();
    User getUserByUserId(int userId);
    List<User> findUsersByName(String name);
	void DeletUserByUser(User user);
}