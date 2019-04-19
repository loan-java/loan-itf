package com.mod.loan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.mod.loan.mapper")
@ComponentScan("com.mod.loan")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
