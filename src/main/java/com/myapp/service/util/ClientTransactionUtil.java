/**
 * 
 */
package com.myapp.service.util;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.dao.AccountDao;
import com.myapp.entity.Account;

/**
 * Util class to validate the input parameters of transactions
 * @author Madhusudan
 * @version : 1.0.0
 */
@Component
public class ClientTransactionUtil {

	@Autowired
	private AccountDao accountDao;
	
	/**
	 * This method is used to validate the account and return account to the calling  method
	 * @param accountNumber input account which needs to be found
	 * @return account if exist or else will return NULL
	 */
	public Account validateAccount(String accountNumber) {
		Account account = null;
		try {
			account = accountDao.findByAccountNumber(accountNumber);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return account;
	}

	/**
	 * This method is used to validate the transfer amount 
	 * @param balance account balance of client
	 * @param amount transfer amount of transaction
	 * @return true if amount is less than or equal to  balance else will return false
	 */
	public boolean validateTransferAount(BigDecimal balance, BigDecimal amount) {
		boolean isValidTransferAmount = false;
		if(balance.compareTo(amount) > 0) {
			isValidTransferAmount = true;
		}
		return isValidTransferAmount;
	}

}
