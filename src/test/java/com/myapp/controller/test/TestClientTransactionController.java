package com.myapp.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.myapp.controller.ClientTransactionController;
import com.myapp.entity.Account;
import com.myapp.entity.Transaction;
import com.myapp.exception.handler.AppExceptionHandler;
import com.myapp.test.config.TestConfig;

/**
 * @author Madhsudan
 *
 */
@RunWith(SpringRunner.class)
public class TestClientTransactionController extends TestConfig{

	private MockMvc mvc;

	@Autowired
	private ClientTransactionController clientTransactionController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(clientTransactionController)
				.setControllerAdvice(new AppExceptionHandler()).build();
	}

	@Test
	public void clientTransact_success() throws Exception {

		Account creditingAccont = persistAccount("fname", "lname", "fname@mail.com", "101");
		Account debitingAccount = persistAccount("fname2", "lname3", "fname2@mail.com", "102");
		Transaction transaction = new Transaction();
		transaction.setCreditingAccount(creditingAccont);
		transaction.setDebitingAccount(debitingAccount);
		BigDecimal amount = new BigDecimal(100);
		transaction.setAmount(amount);
		transaction.setDescription("test transaction");
		mvc.perform(post("/transact").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transaction)))
				.andExpect(status().isOk());
	}
	
	@Test
	public void clientTransact_failure() throws Exception {

		Account creditingAccont = persistAccount("fname", "lname", "fname@mail.com", "101");
		Account debitingAccount = persistAccount("fname2", "lname3", "fname2@mail.com", "102");
		Transaction transaction = new Transaction();
		transaction.setCreditingAccount(creditingAccont);
		transaction.setDebitingAccount(debitingAccount);
		BigDecimal amount = new BigDecimal(1000000);
		transaction.setAmount(amount);
		transaction.setDescription("test transaction");
		mvc.perform(post("/transact").contentType(MediaType.APPLICATION_JSON).content(asJsonString(transaction)))
				.andExpect(status().is4xxClientError());
	}

}
