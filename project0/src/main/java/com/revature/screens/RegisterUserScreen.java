package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		User u = new User();
		System.out.println("Enter new username");
		u.setUsername(scan.nextLine() );
		System.out.println("Enter new password");
		u.setPassword(scan.nextLine() );
		System.out.println("Enter name (format FirstLast)");
		u.setName(scan.nextLine() );
		System.out.println("Enter starting balance");
		double balance = Double.parseDouble(scan.nextLine() );		
		
		System.out.println("Enter passkey to create account as an admin, or press enter to skip.");
		String adminKey = "1138";
		String input = scan.nextLine();
		if (input.equals(adminKey) ) {
			System.out.println("passkey accepted. You are now an admin.");
			u.setIsAdmin(true);
		} else {
			u.setIsAdmin(false);
		}
		
		try {
			u.setBalance(balance);
			u.getTransactions().add("Opening balance: " + balance);
			ud.createUser(u);
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}
		
		return new LoginScreen();
	}

}
