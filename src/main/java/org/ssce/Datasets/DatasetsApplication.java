package org.ssce.Datasets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "org.ssce.Datasets")
@SpringBootApplication
public class DatasetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasetsApplication.class, args);
	}
}
