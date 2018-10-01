package com.myapp.test.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.config.ApplicationConfig;
import com.myapp.entity.Account;
import com.myapp.entity.Client;

/**
 * @author Madhsudan
 *
 */
@WebAppConfiguration
@ContextConfiguration(classes= {ApplicationConfig.class})
@DataJpaTest
@AutoConfigureTestDatabase
public class TestConfig {

	@Autowired
	private TestEntityManager entityManager;

	/*
	 * converts a Java object into JSON representation
	 */
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public Account persistAccount(String fname, String lname, String email, String accnNo) {
		
		Client newClient = new Client();
		newClient.setFirstName(fname);
		newClient.setLastName(lname);
		newClient.setEmailId(email);
		Account newAccont = new Account();
		newAccont.setAccountNumber(accnNo);
		newAccont.setAccountType("SAVING");
		BigDecimal value = new BigDecimal(100000);
		newAccont.setBalance(value);
		List<Account> accounts = new ArrayList<>();
		accounts.add(newAccont);
		newClient.setAccounts(accounts);
		
		entityManager.persistAndFlush(newClient);

		return newAccont;
	}

	public Client persistClient(String fname, String lname, String email) {
		
		Client newClient = new Client();
		newClient.setFirstName(fname);
		newClient.setLastName(lname);
		newClient.setEmailId(email);
		
		entityManager.persistAndFlush(newClient);

		return newClient;
	}
}
