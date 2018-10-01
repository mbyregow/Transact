package com.myapp.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.dao.AccountDao;
import com.myapp.dao.TransactionDao;
import com.myapp.entity.Account;
import com.myapp.entity.Transaction;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.service.ClientTransactionService;
import com.myapp.service.util.ClientTransactionUtil;


/**
 * Implementation class of TransactionService which provides all the business layer implementation
 * 
 * @author Madhusudan
 * @see com.myapp.service.ClientTransactionService
 *
 */
@Service
public class ClientTransactionServiceImpl implements ClientTransactionService{
	
	@Autowired
	private ClientTransactionUtil transactionUtil;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountDao;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = AppServiceException.class)
	public void createTransaction(Transaction transaction) throws AppServiceException{

		Account debitingAccount = transactionUtil.validateAccount(transaction.getDebitingAccount().getAccountNumber());
		Account creditingAccount = transactionUtil.validateAccount(transaction.getCreditingAccount().getAccountNumber());

		if(null == debitingAccount) {
			throw new AppServiceException("Validation Failed","Debiting Account does not exist");
		}

		if(null == creditingAccount) {
			throw new AppServiceException("Validation Failed","Crediting Account does not exist");
		}

		//validate amount transfered 
		boolean isValidTransferAmount = transactionUtil.validateTransferAount(debitingAccount.getBalance(),transaction.getAmount());
		if(isValidTransferAmount) {			
			
			BigDecimal newSenderBalance = debitingAccount.getBalance().subtract(transaction.getAmount());
			debitingAccount.setBalance(newSenderBalance);
			

			BigDecimal newReceiverBalance = creditingAccount.getBalance().add(transaction.getAmount());
			creditingAccount.setBalance(newReceiverBalance);

			//create debit and credit transaction
			createTransaction(transaction,"DEBIT",debitingAccount,creditingAccount);
			createTransaction(transaction,"CREDIT",creditingAccount,debitingAccount);
			//update account to reflect new account balances
			accountDao.save(debitingAccount);
			accountDao.save(creditingAccount);

		}else {
			//throw validation that overdrawing of account is not allowed
			throw new AppServiceException("","Insufficient account balance");
		}
	}

	public void createTransaction(Transaction transaction, String transferType, Account debitingAccount, Account creditingAccount) {
		Transaction transactionObj = new Transaction();
		transactionObj.setAmount(transaction.getAmount());
		transactionObj.setDebitingAccount(debitingAccount);
		transactionObj.setCreditingAccount(creditingAccount);
		transactionObj.setTransactionTime(new Date());
		transactionObj.setDescription(transaction.getDescription());
		transactionObj.setType(transferType);
		transactionDao.save(transactionObj);
	}

}


