package com.revature.daos;

import com.revature.beans.User;

public class CurrentUser {
	
	private static UserDao ud = UserDao.currentUserDao;
	
	private static User currentUser = new User();


	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(String username, String password) {
		if (username == null || password == null ) {
			System.out.println("You are now logged out.");
			currentUser = null;
		} else {
		currentUser = ud.findByUsernameAndPassword(username, password);
		}
	}
	
}
