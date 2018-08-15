package com.revature.screens;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.UserDao;

public class CheckAnyTransHistScreen implements Screen {

	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	
	@Override
	public Screen start() {
		log.debug("started admin transaction history check screen");
		
		System.out.println("Enter the username whose transaction history you'd like to check, or 'end' to return to home screen.");
		String checkingUsername = scan.nextLine();
		if (checkingUsername.equals("end")) {
			return new HomeScreen();
		} else {
			
			System.out.println("Now enter their password.");
			String checkingPassword = scan.next();
		
			User checking = ud.findByUsernameAndPassword(checkingUsername, checkingPassword);
		
			System.out.println("Selected user's transaction history:");
			ArrayList<String> transactions = (ArrayList<String>) checking.getTransactions();
			for (String item : transactions) {
				System.out.println(item);
			}
		
			if (scan.nextLine().equals("end")) {
				return new HomeScreen();
			} else {
				return new CheckAnyTransHistScreen();
			}	

		}
	}
	
}
