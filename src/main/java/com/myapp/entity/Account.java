/**
 * 
 */
package com.myapp.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Entity class to represent the Account table
 * @author Madhusudan
 *
 */

@Entity
@Table(name = "TS_ACCOUNT")
public class Account implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4335392502086254701L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCOUNT_ID")
	private Long accountId;
	
	@Column(name="ACCOUNT_NUMBER")
	@NotNull
	private String accountNumber;
	
	@Column(name="ACCOUNT_TYPE")
	@NotNull
	private String accountType;
	
	@Column(name="BALANCE")
	@NotNull
	@PositiveOrZero
	private BigDecimal balance;

	
	/**
	 * @return the accountId
	 */
	public Long getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


}
