package com.medifinder.medifinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication()
@EnableMethodSecurity()
public class MedicineFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicineFinderApplication.class, args);
    }

}
