package com.revature.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.daos.CurrentUser;
import com.revature.daos.TransactionDao;
import com.revature.daos.UserDao;

public class DepositScreen implements Screen {

	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private TransactionDao td = TransactionDao.currentTransactionDao;

	@Override
	public Screen start() {
		log.debug("started deposit screen");
		User u = CurrentUser.getCurrentUser();
		Transaction t = new Transaction();

		System.out.println("Your current credit balance is:");
		System.out.println(u.getBalance());
		System.out.println("Enter amount to deposit, or 'end' to return to home screen");

		String deposit = scan.nextLine();
		
		if (deposit.equals("end")) {
			return new HomeScreen();
		} else {
			System.out.println("Enter today's date (mm/dd/yyyy)");
			String today = scan.nextLine();
			
			double depositNum = Double.parseDouble(deposit);
			double currentBal = u.getBalance();
			currentBal = currentBal + depositNum;
			u.setBalance(currentBal);
			
			ud.updateUser(u);
			double zero = 0;
			
			t.setTransactionDate(today);
			System.out.println(u.getUserId() );
			t.setUserId(u.getUserId());
			t.setWithdrawalAmount(zero);
			t.setDepositAmount(depositNum);
			
			td.createTransaction(t);
			
			
			return new DepositScreen();
		}

	}

}
