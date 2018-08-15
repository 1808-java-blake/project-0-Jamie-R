package com.revature.daos;

import com.revature.beans.User;

/**
 * 
 * Takes in a user object and will persist that user
 *
 */

public interface UserDao {
	public static final UserDao currentUserDao = new UserSerializer();
	
	void createUser(User u);
	User findByUsernameAndPassword(String username, String password);
	void updateUser(User u);
	
}