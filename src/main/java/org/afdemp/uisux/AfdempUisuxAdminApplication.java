package org.afdemp.uisux;

import org.afdemp.uisux.utility.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class AfdempUisuxAdminApplication implements CommandLineRunner{
	
	@Autowired
	private DataGenerator dataGenerator;

	public static void main(String[] args) {
		SpringApplication.run(AfdempUisuxAdminApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		
		dataGenerator.generate();
		
	}
	
	
	
}
