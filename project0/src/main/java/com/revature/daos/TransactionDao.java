package com.revature.daos;

import java.util.List;

import com.revature.beans.Transaction;

public interface TransactionDao {
public static final TransactionDao currentTransactionDao = new TransactionDaoJdbc();
	
	public void createTransaction(Transaction t);
	List<Transaction> findByUserId(int id);
	
}
