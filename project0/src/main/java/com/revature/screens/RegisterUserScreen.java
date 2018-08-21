package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;

public class RegisterUserScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private TransactionDao td = TransactionDao.currentTransactionDao;
	private Logger log = Logger.getRootLogger();

	@Override
	public Screen start() {
		User u = new User();
		Transaction t = new Transaction();
		
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
		
		
		
//		try {
			u.setBalance(balance);
			double zero = 0;
			ud.createUser(u);
			
			System.out.println("Enter today's date (mm/dd/yyyy)");
			t.setTransactionDate(scan.nextLine());
			t.setUserId(u.getUserId() );
			t.setDepositAmount(balance);
			t.setWithdrawalAmount(zero);
			
			System.out.println("before createTransaction sysout line");
			log.trace("before createTransaction");
			td.createTransaction(t);
			System.out.println("after createTransaction sysout line");
			System.out.println(td.findByUserId(u.getUserId()) );
			
//		} catch (NumberFormatException e) {
//			System.out.println("Invalid number");
//		}
		
		return new LoginScreen();
	}

}
