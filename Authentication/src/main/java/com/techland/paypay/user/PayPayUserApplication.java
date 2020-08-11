package com.techland.paypay.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.techland.paypay")
public class PayPayUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(PayPayUserApplication.class, args);
	}

}
