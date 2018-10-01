package com.myapp.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * This is the entry point for Transfer Management Service Application
 * @author Madhusudan
 *
 */

@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
@ComponentScan(basePackages="com.myapp") 
@EntityScan("com.myapp.entity")
@EnableJpaRepositories("com.myapp.dao")
public class ApplicationConfig {

	/**
	 * @param args list of input argument for starting of the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApplicationConfig.class, args);
	}

}
