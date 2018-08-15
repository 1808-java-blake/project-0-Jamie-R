package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.daos.CurrentUser;

public class LoginScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private Logger log = Logger.getRootLogger();
	
	@Override
	public Screen start() {
		log.debug("started login screen");
		System.out.println();
		System.out.println("Enter Username or type Register to sign up: ");
		String username = scan.nextLine();
		log.trace("username = " + username);
		if ("register".equalsIgnoreCase(username)) {
			return new RegisterUserScreen();
		}
		
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		
		
		log.debug("received users credentials");
		CurrentUser.setCurrentUser(username, password);
		
		if(CurrentUser.getCurrentUser() != null) {
			log.info("user successfully logged in");
			return new HomeScreen();
		}
		
		System.out.println("unable to login");
		return this;
	}
}
