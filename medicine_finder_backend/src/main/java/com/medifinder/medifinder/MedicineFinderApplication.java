package com.medifinder.medifinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class MedicineFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineFinderApplication.class, args);
	}

}
