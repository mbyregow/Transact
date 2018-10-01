/**
 * 
 */
package com.myapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.dao.ClientDao;
import com.myapp.entity.Client;
import com.myapp.exception.handler.AppServiceException;
import com.myapp.exception.handler.ResourceException;
import com.myapp.service.ClientManagerService;

/**
 * Implementation class of ClientService which provides all the business layer implementation
 * 
 * @author Madhusudan
 * @see com.myapp.service.ClientManagerService
 *
 */
@Service
public class ClientManagerServiceImpl implements ClientManagerService{

	@Autowired
	private ClientDao clientDao;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = AppServiceException.class)
	public Client getClient(Long customerId) throws ResourceException,AppServiceException {
		Client customer = null;
		try {
			Optional<Client> customers = clientDao.findById(customerId);
			if(!customers.isPresent()) {
				throw new ResourceException("Validation Error","Client does not exist");
			}
			customer = customers.get();
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while fecthing client details");
		}

		return customer;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = AppServiceException.class)
	public Client createClient(Client client) throws AppServiceException{
		try {
			return clientDao.save(client);
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while saving client details");
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = AppServiceException.class)
	public List<Client> findAllClients() throws AppServiceException, ResourceException {
		try {
			List<Client> clients = (List<Client>) clientDao.findAll();
			if(null == clients || clients.isEmpty()) {
				throw new ResourceException("Validation Error","No Client details found");
			}
			return clients;
		}catch(HibernateException e) {
			throw new AppServiceException("Unexpected Error","Error while fetching all clients details");
		}
	}
}
