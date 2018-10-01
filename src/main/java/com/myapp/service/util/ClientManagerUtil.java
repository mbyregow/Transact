/**
 * 
 */
package com.myapp.service.util;

import java.math.BigDecimal;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Component;

import com.myapp.entity.Account;
import com.myapp.entity.Client;

/**
 * Util class to validate the input parameters of client
 * 
 * @author Madhusudan
 * @version : 1.0.0
 */
@Component
public class ClientManagerUtil {

	/**
	 * This method validates if the client input is valid or not
	 * 
	 * @param client input client details
	 * @return true if client data is valid else will return false
	 */
	public static boolean validateCustomerData(Client client) {
		boolean isValidCustomerData = true;
		boolean isValidEmailAddress = isValidEmailAddress(client.getEmailId());
		boolean isValidBalance = true;

		for (Account account : client.getAccounts()) {
			isValidBalance = (account.getBalance().compareTo(BigDecimal.ZERO) != -1) ? true : false;
			if (!isValidBalance) {
				break;
			}
		}

		if ((!isValidEmailAddress)) {
			isValidCustomerData = false;
		}
		return isValidCustomerData;
	}

	/**
	 * This method validates if the given email ID is valid or not
	 * 
	 * @param email input email ID
	 * @return true if email is valid else will return false
	 */
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

}
