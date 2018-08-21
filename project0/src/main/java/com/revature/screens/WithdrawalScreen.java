package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.daos.CurrentUser;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;

public class WithdrawalScreen implements Screen {
	
	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private TransactionDao td = TransactionDao.currentTransactionDao;

	@Override
	public Screen start() {
		log.debug("started withdrawal screen");
		User u = CurrentUser.getCurrentUser();
		Transaction t = new Transaction();

		System.out.println("Your current credit balance is:");
		System.out.println(u.getBalance());
		System.out.println("Enter amount to withdraw, or 'end' to return to home screen");

		String withdrawal = scan.nextLine();
		
		if (withdrawal.equals("end")) {
			return new HomeScreen();
		} else {
			System.out.println("Enter today's date (mm/dd/yyyy)");
			String today = scan.nextLine();
			
			double withdrawalNum = Double.parseDouble(withdrawal);
			double currentBal = u.getBalance();
			currentBal = currentBal - withdrawalNum;
			u.setBalance(currentBal);

			ud.updateUser(u);
			
			t.setTransactionDate(today);
			t.setUserId(u.getUserId());
			t.setWithdrawalAmount(withdrawalNum);
			t.setDepositAmount(0);
			
			td.createTransaction(t);
			
			return new WithdrawalScreen();
		}
	}
	
}
