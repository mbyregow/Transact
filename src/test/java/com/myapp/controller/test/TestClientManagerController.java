package com.myapp.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.myapp.controller.ClientManagerController;
import com.myapp.entity.Client;
import com.myapp.exception.handler.AppExceptionHandler;
import com.myapp.test.config.TestConfig;

/**
 * @author Madhsudan
 *
 */
@RunWith(SpringRunner.class)
public class TestClientManagerController extends TestConfig {

	private MockMvc mvc;

	@Autowired
	private ClientManagerController clientManagerController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(clientManagerController).setControllerAdvice(new AppExceptionHandler())
				.build();
	}

	@Test
	public void createClient_success() throws Exception {

		Client newClient = new Client();
		newClient.setFirstName("fname");
		newClient.setLastName("lname");
		newClient.setEmailId("mail.mail@test.com");

		mvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newClient)))
				.andExpect(status().isOk()).andExpect(jsonPath("firstName", is(newClient.getFirstName())));
	}

	@Test
	public void createClient_error() throws Exception {

		Client newClient = new Client();
		newClient.setFirstName("fname");
		newClient.setLastName("lname");

		mvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newClient)))
				.andExpect(status().is4xxClientError()).andExpect(jsonPath("message", is("Validation Failed")));
	}

	@Test
	public void getClient_success() throws Exception {

		Client newClient = persistClient("fname", "lname", "fname@mail.com");

		mvc.perform(get("/clients/" + newClient.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("firstName", is(newClient.getFirstName())));
	}

	@Test
	public void getClient_failure() throws Exception {

		mvc.perform(get("/clients/2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andExpect(jsonPath("details", is("Client does not exist")));

	}

}