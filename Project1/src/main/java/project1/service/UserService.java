package project1.service;

import project1.dao.UserDao;
import project1.model.User;

public class UserService {
	public static User getUser(String username, String password) {
		User temp = UserDao.getUser(username);
		if (temp!=null && temp.getPassword().equals(password)) {
			return UserDao.getUser(username);
		}
		
		return null;
	}
}
