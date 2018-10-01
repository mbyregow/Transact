/**
 * 
 */
package com.myapp.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity class to represent the Transaction table
 * 
 * @author Madhusudan
 *
 */

@Entity
@Table(name = "TS_TRANSACTION")
public class Transaction implements AppEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2504157429464570734L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TRANSACTION_TIME")
	private Date transactionTime;

	@Column(name = "DESCRIPTION")
	@NotNull(message = "Please enter description")
	private String description;

	@Column(name = "TRANSACTION_TYPE")
	private String type;

	@Column(name = "DEBITING_ACCNT_ID")
	private Long debitingAccntId;

	@Column(name = "CREDITING_ACCNT_ID")
	private Long creditingAccntId;

	@Column(name = "AMOUNT")
	@NotNull
	private BigDecimal amount;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TS_DEBITING_ACCNT_ID")
	@NotNull(message = "Debiting account can not be empty")
	private Account debitingAccount;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TS_CREDITING_ACCNT_ID")
	@NotNull(message = "Crediting account can not be empty")
	private Account creditingAccount;

	/**
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public Date getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime
	 */
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return
	 */
	public Long getDebitingAccntId() {
		return debitingAccntId;
	}

	/**
	 * @param debitingAccntId
	 */
	public void setDebitingAccntId(Long debitingAccntId) {
		this.debitingAccntId = debitingAccntId;
	}

	/**
	 * @return
	 */
	public Long getCreditingAccntId() {
		return creditingAccntId;
	}

	/**
	 * @param creditingAccntId
	 */
	public void setCreditingAccntId(Long creditingAccntId) {
		this.creditingAccntId = creditingAccntId;
	}

	/**
	 * @return
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return
	 */
	public Account getDebitingAccount() {
		return debitingAccount;
	}

	/**
	 * @param debitingAccount
	 */
	public void setDebitingAccount(Account debitingAccount) {
		this.debitingAccount = debitingAccount;
	}

	/**
	 * @return
	 */
	public Account getCreditingAccount() {
		return creditingAccount;
	}

	/**
	 * @param creditingAccount
	 */
	public void setCreditingAccount(Account creditingAccount) {
		this.creditingAccount = creditingAccount;
	}

	
}
