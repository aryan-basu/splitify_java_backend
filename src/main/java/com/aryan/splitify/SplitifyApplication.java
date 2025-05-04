package com.aryan.splitify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
public class SplitifyApplication {

	public static void main(String[] args) {


		// Set as system properties so Spring can use
//		Dotenv dotenv = Dotenv.configure().load();
//		System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));
//		System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));
		SpringApplication.run(SplitifyApplication.class, args);

	}


	@Bean
	public PlatformTransactionManager Test(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

}
