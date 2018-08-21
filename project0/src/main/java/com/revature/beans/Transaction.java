package com.revature.beans;

public class Transaction {
	
	private int transactionId;
	private String transactionDate;
	private int userId;
	private double depositAmount;
	private double withdrawalAmount;
	
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate + ", userId="
				+ userId + ", depositAmount=" + depositAmount + ", withdrawalAmount=" + withdrawalAmount + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(depositAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((transactionDate == null) ? 0 : transactionDate.hashCode());
		result = prime * result + transactionId;
		result = prime * result + userId;
		temp = Double.doubleToLongBits(withdrawalAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (Double.doubleToLongBits(depositAmount) != Double.doubleToLongBits(other.depositAmount))
			return false;
		if (transactionDate == null) {
			if (other.transactionDate != null)
				return false;
		} else if (!transactionDate.equals(other.transactionDate))
			return false;
		if (transactionId != other.transactionId)
			return false;
		if (userId != other.userId)
			return false;
		if (Double.doubleToLongBits(withdrawalAmount) != Double.doubleToLongBits(other.withdrawalAmount))
			return false;
		return true;
	}


	public int getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}


	public String getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public double getDepositAmount() {
		return depositAmount;
	}


	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}


	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}


	public void setWithdrawalAmount(double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}


	public Transaction(int transactionId, String transactionDate, int userId, double depositAmount,
			double withdrawalAmount) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.userId = userId;
		this.depositAmount = depositAmount;
		this.withdrawalAmount = withdrawalAmount;
	}


	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
