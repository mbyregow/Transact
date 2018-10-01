/**
 * 
 */
package com.myapp.service;

import java.util.List;

import com.myapp.entity.Client;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.exception.handler.ResourceException;

/**
 * 
 * Interface to have all the business methods related to client management
 * @author Madhusudan
 * @version : 1.0.0
 *
 */
public interface ClientManagerService{
	
	/**
	 * This method is used to find a client by Id
	 * @param clientId : the client ID
	 * @return the client with matching id else null
	 * @throws ResourceException resource not found error
	 * @throws AppServiceException wrapper exception for service errors
	 */
	public Client getClient(Long clientId) throws ResourceException, AppServiceException;
	
	/**
	 * This method is used to create a new client.
	 * @param client : new client details
	 * @return the newly created client
	 * @throws AppServiceException wrapper exception for service errors
	 */
	public Client createClient(Client customer) throws AppServiceException;

	/**
	 * This method is used to get all the client in the application
	 * @return list of clients in the system if exists or NULL
	 * @throws AppServiceException  wrapper exception for service errors
	 * @throws ResourceException wrapper exception for database errors
	 */
	public List<Client> findAllClients()throws AppServiceException, ResourceException;;
	

}
