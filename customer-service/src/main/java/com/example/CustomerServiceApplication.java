package com.example;

import com.example.config.GlobalConfig;
import com.example.entities.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({GlobalConfig.class})
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner startCommandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			List<Customer> customerList = List.of(
					Customer.builder()
							.firstName("Oussama")
							.lastName("Aliev")
							.email("aliev@gmail.com")
							.build(),
					Customer.builder()
							.firstName("Khadija")
							.lastName("Bel")
							.email("Khadija@gmail.com")
							.build()
			);
			customerRepository.saveAll(customerList);
		};
	}

}
