/**
 * 
 */
package com.myapp.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Transaction;

/**
 * 
 * This class provide default implementation of the basic CURD operations. 
 * 
 * @author Madhusudan
 *
 */

@Repository
public interface TransactionDao extends CrudRepository<Transaction, Long>{

}
