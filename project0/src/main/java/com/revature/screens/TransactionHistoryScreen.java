package com.revature.screens;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.CurrentUser;

public class TransactionHistoryScreen implements Screen {

	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	
	@Override
	public Screen start() {
		log.debug("started transaction history screen");
		User u = CurrentUser.getCurrentUser();
		
		System.out.println("Past transactions:");
		ArrayList<String> transactions = (ArrayList<String>) u.getTransactions();
		for (String item : transactions) {
			System.out.println(item);
		}
		
		System.out.println("Type 'end' to return to home screen.");
		
		if (scan.nextLine().equals("end")) {
			return new HomeScreen();
		} else {
			return new CheckMyBalanceScreen();
		}	

	}
}
