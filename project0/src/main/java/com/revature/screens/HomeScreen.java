package com.revature.screens;

import java.util.Scanner;

import com.revature.beans.User;
import com.revature.daos.CurrentUser;

public class HomeScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	
	public Screen start() {
		User u = CurrentUser.getCurrentUser();
		
		System.out.println("Welcome to the Mos Eisley Credit Exchange! Where the rules are few and "
				+ "the interest is criminal.");
				System.out.println("(not really. This is an extremely legal operation)");
		System.out.println("Choose from the following options:");
		System.out.println("Enter 1 to check credit balance (way lower than you might think it should be, by the way)");
		System.out.println("Enter 2 to deposit your credits (please, we could use some more right now...)");
		System.out.println("Enter 3 to withdraw your credits");
		System.out.println("Enter 4 to view your transaction history");
		System.out.println("Enter 5 to log out (come back soon...or we'll come to you!)");
		
		System.out.println("");
		System.out.println("Admins only!");
		System.out.println("Enter 6 to view a victi...er, user's credit balance");
		System.out.println("Enter 7 to view an unwise investor's transaction history");
		
		String selection = scan.nextLine();
		boolean adminCheck = u.getIsAdmin();
		
		switch (selection) {
		case "1":
			return new CheckMyBalanceScreen();
		case "2":
			return new DepositScreen();
		case "3":
			return new WithdrawalScreen();
		case "4":
			return new TransactionHistoryScreen();
		case "5":
			CurrentUser.setCurrentUser(null, null);
			return new LoginScreen();
		case "6":
			if (adminCheck == true) {
				return new CheckAnyBalanceScreen();
			} else {
				System.out.println("Hey! You're not allowed in there!");
				return new HomeScreen();
			}
		case "7":
			if (adminCheck == true) {
				return new CheckAnyTransHistScreen();
			} else {
				System.out.println("Hey! You're not allowed in there!");
				return new HomeScreen();
			}
		default:
			break;
		}
		
		return this;
	}

}
	