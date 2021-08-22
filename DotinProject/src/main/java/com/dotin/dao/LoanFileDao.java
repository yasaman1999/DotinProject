package com.dotin.dao;

import com.dotin.bean.Customer;
import com.dotin.bean.LoanFile;
import com.dotin.bean.LoanType;
import com.dotin.db.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoanFileDao {

	private static LoanFileDao loanFileDao = new LoanFileDao();

	private LoanFileDao() {
	}

	public static LoanFileDao getInstance() {
		return loanFileDao;
	}

	public void addLoanFileDetails(int contractPeriod, int amount, LoanType loanType, Customer customer) {
		Session session = HibernateManager.openSession();
		try {
			Transaction transaction = session.beginTransaction();

			LoanFile loanFile = new LoanFile();
			loanFile.setContractPeriod(contractPeriod);
			loanFile.setAmount(amount);
			loanFile.setCustomer(customer);
			loanFile.setLoanType(loanType);
			session.save(loanFile);

			transaction.commit();

		} finally {
			session.close();
		}
	}
}

