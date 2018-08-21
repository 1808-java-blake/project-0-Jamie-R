package com.revature.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.beans.User;
import com.revature.daos.CurrentUser;
import com.revature.daos.TransactionDao;

public class TransactionHistoryScreen implements Screen {

	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private TransactionDao td = TransactionDao.currentTransactionDao;
	
	@Override
	public Screen start() {
		log.debug("started transaction history screen");
		User u = CurrentUser.getCurrentUser();
		
		System.out.println("Past transactions:");
		
		log.trace("about to output transaction history");
		List<Transaction> userHistory = new ArrayList<Transaction>(td.findByUserId(u.getUserId() ) ) ;
		System.out.println(userHistory);
		for (Transaction t : userHistory ) {
			String date = t.getTransactionDate();
			int u_id = t.getUserId();
			double deposit = t.getDepositAmount();
			double withdrawal = t.getWithdrawalAmount();
			
			System.out.println(date + " User ID: " + u_id + " Amount deposited: " + deposit + ". Amount withdrawn: " + withdrawal + "." );
		}		
		log.trace("after output transaction history");
		
		System.out.println("Type 'end' to return to home screen.");
		
		if (scan.nextLine().equals("end")) {
			return new HomeScreen();
		} else {
			return new CheckMyBalanceScreen();
		}	

	}
}
