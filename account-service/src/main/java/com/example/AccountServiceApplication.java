package com.example;

import com.example.entities.BankAccount;
import com.example.enums.AccountType;
import com.example.feignClient.CustomerFeignClient;
import com.example.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	@Autowired
	private Environment environment;

	public AccountServiceApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner startCommandLineRunner(BankAccountRepository bankAccountRepository, CustomerFeignClient customerFeignClient) {
		return args -> {
			customerFeignClient.getAllCustomers().forEach(c -> {
				BankAccount bankAccount1 = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.balance(Math.random() * 100000)
						.createdAt(LocalDate.now())
						.currency("MAD")
						.type(AccountType.CURRENT_ACCOUNT)
						.customerId(c.getId())
						.build();
				BankAccount bankAccount2 = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.balance(Math.random() * 65000)
						.createdAt(LocalDate.now())
						.currency("MAD")
						.type(AccountType.SAVING_ACCOUNT)
						.customerId(c.getId())
						.build();
				bankAccountRepository.save(bankAccount1);
				bankAccountRepository.save(bankAccount2);
			});

			System.out.println("------------------------------");
			for (String profile : environment.getActiveProfiles()) {
				System.out.println(".......... Active Profile: " + profile);
			}
			System.out.println("------------------------------");
		};
	}

}
