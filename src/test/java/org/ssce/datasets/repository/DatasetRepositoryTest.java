package org.ssce.datasets.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.ssce.datasets.model.Dataset;
import org.ssce.datasets.respository.DatasetRepository;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DatasetRepositoryTest {

    @Autowired
    private DatasetRepository datasetRepository;
    Dataset dataset;

    @BeforeEach
    void setup(){
        dataset = new Dataset();
        dataset.setUuid(UUID.fromString("19a53566-6990-4a3f-8e82-585412df00b2"));
        dataset.setName("datasetEmployeeTwo");
        dataset.setDataSchema(new HashMap<>());
        dataset.setRouterConfig(new HashMap<>());
        dataset.setStatus(Dataset.Status.DRAFT);
        dataset.setCreatedBy("datasetEmployeeTwo");
        dataset.setUpdatedBy("datasetEmployeeTwo");
        dataset.setCreatedDate(LocalDateTime.now());
        dataset.setUpdatedDate(LocalDateTime.now());
        datasetRepository.save(dataset);
    }

    @AfterEach
    void tearDown(){

    }

    @Test
    @Rollback(value = false)
    void testDatasetFindByUuid(){
        Dataset data = datasetRepository.findByUuid(UUID.fromString("19a53566-6990-4a3f-8e82-585412df00b2"));
        assertThat(data.getName()).isEqualTo(dataset.getName());
        assertThat(data.getUuid()).isEqualTo(dataset.getUuid());
    }

    @Test
    void testDatasetFindByUuidError(){
       Optional<Dataset> optional = Optional.ofNullable(datasetRepository.findByUuid(UUID.fromString("46fc6baf-fefa-4990-9bfa-f228a039fbca")));
        assertThat(optional.isEmpty()).isTrue();
    }
}
