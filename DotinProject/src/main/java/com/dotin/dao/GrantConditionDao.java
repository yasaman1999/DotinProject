package com.dotin.dao;

import com.dotin.bean.GrantCondition;
import com.dotin.bean.LoanType;
import com.dotin.db.HibernateManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class GrantConditionDao {

	private static GrantConditionDao grantConditionDao = new GrantConditionDao();

	private GrantConditionDao() {
	}

	public static GrantConditionDao getInstance() {
		return grantConditionDao;
	}

	public void addGrantConditionDetails(Set<GrantCondition> grantConditionSet, String loanName, int interestRate) {
		Session session = HibernateManager.openSession();
		try {
			Transaction transaction = session.beginTransaction();

			LoanType loanType = new LoanType();

			loanType.setName(loanName);
			loanType.setInterestRate(interestRate);
			session.save(loanType);

			for (GrantCondition grantcondition : grantConditionSet) {
				grantcondition.setLoanType(loanType);
				session.save(grantcondition);
			}
			loanType.setGrantConditions(grantConditionSet);
			session.update(loanType);

			transaction.commit();

		} finally {
			session.close();
		}
	}
}

