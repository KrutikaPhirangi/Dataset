package org.ssce.Datasets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "org.ssce.Datasets")
@SpringBootApplication
public class DatasetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatasetsApplication.class, args);
	}

}
