/**
 * 
 */
package com.myapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Client;

/**
 * 
 * This class provide default implementation of the basic CURD operations provided by CrudRepository. 
 * Also it contains all the blue print of business methods for Client entity
 * @author Madhusudan
 *
 */

@Repository
public interface ClientDao extends CrudRepository<Client, Long>{


}
