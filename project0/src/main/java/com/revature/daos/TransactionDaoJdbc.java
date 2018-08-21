package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.beans.Transaction;
import com.revature.util.ConnectionUtil;

public class TransactionDaoJdbc implements TransactionDao {
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

	@Override
	public void createTransaction(Transaction t) {
		log.trace("start of create transaction");
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO tran (transaction_date, user_id, deposit_amount, withdrawal_amount) VALUES (?,?,?,?)");
			ps.setString(1, t.getTransactionDate());
			ps.setInt(2, t.getUserId());
			ps.setDouble(3, t.getDepositAmount());
			ps.setDouble(4, t.getWithdrawalAmount());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new transaction");
		}

	}

	@Override
	public List<Transaction> findByUserId(int id) {
		try (Connection conn = cu.getConnection()) {
			
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM tran WHERE user_id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			List<Transaction> history = new ArrayList<Transaction>();
					
			while(rs.next()) {
				Transaction t = new Transaction();
				
				t.setTransactionDate(rs.getString("transaction_date"));	
				t.setUserId(rs.getInt("user_id"));
				t.setDepositAmount(rs.getDouble("deposit_amount") );
				t.setWithdrawalAmount(rs.getDouble("withdrawal_amount"));
				
				history.add(t);
			} 
			
			return history;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
