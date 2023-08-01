package org.ssce.datasets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "org.ssce.datasets")
@SpringBootApplication
public class DatasetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasetsApplication.class, args);
	}

}
