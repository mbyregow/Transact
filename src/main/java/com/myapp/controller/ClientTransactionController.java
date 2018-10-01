/**
 * 
 */
package com.myapp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.entity.Transaction;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.service.ClientTransactionService;

/**
 * Rest controller for handling all the operations related to Client Transaction
 * 
 * @author Madhusudan
 * @version : 1.0.0
 * 
 *	REST URLS: 
 *
 *  1. POST : /transact -> to transact between two client accounts
 */

@RestController
public class ClientTransactionController {
	
	@Autowired
	private ClientTransactionService transactionService;

	/**
	 * This Rest controller is used to do transaction between two accounts
	 * @param transaction details of the transaction
	 * @throws AppServiceException 
	 */
	@RequestMapping(value = "transact", 
			method = RequestMethod.POST, 
			headers = "Accept=application/json", 
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void createTransaction(@Valid @RequestBody Transaction transaction) throws AppServiceException{
		try {
			transactionService.createTransaction(transaction);
		} catch (AppServiceException ex) {
			throw ex;
		}
	}
	
}
