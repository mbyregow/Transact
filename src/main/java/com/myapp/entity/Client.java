/**
 * 
 */
package com.myapp.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Entity class to represent the Client table
 * @author Anshu Kumar
 *
 */

@Entity
@Table(name = "TS_CLIENT")
public class Client implements AppEntity{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 664562226642737438L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="FIRST_NAME",nullable=false)
	@NotNull
	@Size(min=1, message="First Name should have atleast 1 characters")
	private String firstName;
	
	@Column(name="LAST_NAME",nullable=false)
	@NotNull
	@Size(min=1, message="Last Name should have atleast 1 characters")
	private String lastName;
	
	@Column(name="EMAIL_ID")
	@NotNull
	@Email(message="Invalid email id")
	private String emailId;
	
	@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name= "TS_CLIENT_ID")
	private List<Account> accounts;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	/**
	 * @return the accounts
	 */
	public List<Account> getAccounts() {
		return accounts;
	}

	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	
}

