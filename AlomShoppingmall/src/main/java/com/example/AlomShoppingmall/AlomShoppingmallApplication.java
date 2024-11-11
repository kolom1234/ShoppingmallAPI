package com.example.AlomShoppingmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class AlomShoppingmallApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlomShoppingmallApplication.class, args);
	}
}
