package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.daos.CurrentUser;
import com.revature.daos.UserDao;

public class WithdrawalScreen implements Screen {
	
	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		log.debug("started withdrawal screen");
		User u = CurrentUser.getCurrentUser();

		System.out.println("Your current credit balance is:");
		System.out.println(u.getBalance());
		System.out.println("Enter amount to withdraw, or 'end' to return to home screen");

		String input = scan.nextLine();
		
		if (input.equals("end")) {
			return new HomeScreen();
		} else {
			double inputNum = Double.parseDouble(input);
			double currentBal = u.getBalance();
			currentBal = currentBal - inputNum;
			u.setBalance(currentBal);
			u.getTransactions().add("Withdrew " + inputNum + " credits. New total: " + u.getBalance() );
			ud.updateUser(u);
			
			return new WithdrawalScreen();
		}
	}
	
}