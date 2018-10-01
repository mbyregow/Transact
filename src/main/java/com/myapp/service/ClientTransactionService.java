/**
 * 
 */
package com.myapp.service;

import com.myapp.entity.Transaction;
import com.myapp.exception.handler.AppServiceException;

/**
 * 
 * Interface to have all the business methods related to transaction
 * @author Madhusudan
 * @version : 1.0.0
 *
 */
public interface ClientTransactionService {

	/**
	 * This method is used to do transaction between two accounts
	 * @param transaction details of the transaction
	 * @throws TransactionServiceException dao layer exception wrapper
	 * @throws ResourceException resource not found exception
	 */
	public void createTransaction(Transaction transaction) throws AppServiceException;
	

}
