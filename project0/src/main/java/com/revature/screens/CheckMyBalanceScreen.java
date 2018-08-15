package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.CurrentUser;

public class CheckMyBalanceScreen implements Screen {
	
	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	
	@Override
	public Screen start() {
		log.debug("started self balance check screen");
		User u = CurrentUser.getCurrentUser();
		
		System.out.println("Your current credit balance is:");
		System.out.println(u.getBalance());
		System.out.println("Type 'end' to return to home screen.");
		
		if (scan.nextLine().equals("end")) {
			return new HomeScreen();
		} else {
			return new CheckMyBalanceScreen();
		}	

	}
}