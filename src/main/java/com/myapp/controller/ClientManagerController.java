/**
 * 
 */
package com.myapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.entity.Client;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.exception.handler.ResourceException;
import com.myapp.service.ClientManagerService;

/**
 * Rest controller for handling all the operations related to client it can be
 * used to create and fetch the client details
 * 
 * @author Madhusudan
 * @version : 1.0.0
 * 
 *          REST URLS:
 *
 *1. GET :/clients/{clientId} -> to retrieve information of a given client ID 
 *2. POST:/clients -> to crate a client ID 
 *3. GET :/clients -> to retrieve information of all the client
 *
 */

@RestController
public class ClientManagerController {

	@Autowired
	private ClientManagerService clientManagerService;

	/**
	 * This method is use to get the client details by providing the client ID
	 * 
	 * @param clientId
	 * @return the client details if found else will return Resource not found
	 *         exception
	 * @throws AppServiceException
	 * @throws ResourceException
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "clients/{clientId}", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Client findClient(@PathVariable String clientId) throws AppServiceException,ResourceException, NumberFormatException{
		Client client = null;
		try {
			client = clientManagerService.getClient(Long.valueOf(clientId));
		} catch (NumberFormatException | ResourceException | AppServiceException ex) {
			throw ex;
		}

		return client;
	}

	/**
	 * This method is used to create a new client
	 * 
	 * @param client
	 *            input details of customer
	 * @return the newly created client Details
	 * @throws AppServiceException
	 */
	@RequestMapping(value = "clients", method = RequestMethod.POST, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public Client createNewClient(@Valid @RequestBody Client client) throws AppServiceException {
		Client clientObj = null;
		try {
			clientObj = clientManagerService.createClient(client);
		} catch (AppServiceException ex) {
			throw ex;
		}
		return clientObj;
	}

	/**
	 * This Rest controller is used to fetch all the clients in the application
	 * 
	 * @return list of clients if exist or No data found exception
	 * @throws ResourceException
	 * @throws AppServiceException
	 */
	@RequestMapping(value = "clients", method = RequestMethod.GET, headers = "Accept=application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public List<Client> findAllClients() throws ResourceException, AppServiceException {
		List<Client> clients = null;
		try {
			clients = clientManagerService.findAllClients();
		} catch (AppServiceException | ResourceException ex) {
			throw ex;
		}
		return clients;
	}

}
